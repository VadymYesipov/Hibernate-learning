package com.aimprosoft.yesipov.hibernate.web;

import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.impl.MySQLDepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.impl.MySQLEmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.repository.entity.EmployeeEntity;
import com.aimprosoft.yesipov.hibernate.web.service.AddEntityService;
import com.aimprosoft.yesipov.hibernate.web.service.ListService;
import com.aimprosoft.yesipov.hibernate.web.service.RemoveEntityService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger log = Logger.getLogger(Controller.class);

    private DepartmentDAO departmentDAO = MySQLDepartmentDAO.getMySQLDepartmentDAO();
    private EmployeeDAO employeeDAO = MySQLEmployeeDAO.getMySQLEmployeeDAO();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        log.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        // execute command and get forward address
        String forward = executeService(commandName, request);
        log.trace("Forward address --> " + forward);

        log.debug("Controller finished, now go to forward address --> " + forward);

        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }

    private String executeService(String name, HttpServletRequest request) {
        switch (name) {
            case "list":
                return request.getParameter("name").equals("departments") ?
                        Path.DEPARTMENTS_JSP :
                        Path.ALL_EMPLOYEES_JSP;
            case "filteredList":
                executeFilteredListService(request);
                return Path.EMPLOYEES_JSP;
            case "addEdit":
                return request.getParameter("name").equals("departments") ?
                        Path.ADD_EDIT_DEPARTMENT :
                        Path.ADD_EDIT_EMPLOYEE;
            case "removeEmployee":
                return removeEmployeeAndReturnPath(request);
            case "removeDepartment":
                return removeDepartmentAndReturnPath(request);
            case "addEmployee":
                return addEmployeeAndReturnPath(request);
            case "addDepartment":
                return addDepartmentAndReturnPath(request);
            case "editEmployee":
                break;
            case "editDepartment":
                break;
        }
        return null;
    }

    private void executeFilteredListService(HttpServletRequest request) {

        Integer id = Integer.valueOf(request.getParameter("id"));

        ListService listService = new ListService(departmentDAO, employeeDAO);

        request.getServletContext().setAttribute("employeeList",
                listService.getFilteredList(id));
    }

    private String removeEmployeeAndReturnPath(HttpServletRequest request) {
        String errorMessage = null;
        String forward = Path.ERROR_PAGE;
        String email = request.getParameter("email").trim();

        List<EmployeeEntity> employees = (List<EmployeeEntity>) request.getServletContext().getAttribute("employeeList");

        EmployeeEntity employee = employees.stream()
                .filter(x -> email.equals(x.getEmail()))
                .findAny()
                .orElse(null);

        if (employee == null) {
            errorMessage = "An employee with such email doesn't exist";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            employees = new RemoveEntityService(employeeDAO).execute(employee);

            log.trace("Employee size = " + employees.size());
            request.getServletContext().setAttribute("employeeList", employees);

            forward = Path.EMPLOYEES_JSP;
        }

        return forward;
    }

    private String removeDepartmentAndReturnPath(HttpServletRequest request) {
        log.debug("Command starts");

        String errorMessage = null;
        String forward = Path.ERROR_PAGE;

        Integer id = Integer.valueOf(request.getParameter("id"));

        request.setAttribute("remove_ID", id);

        List<DepartmentEntity> departments = (List<DepartmentEntity>) request.getServletContext().getAttribute("departmentList");
        List<EmployeeEntity> employees = (List<EmployeeEntity>) request.getServletContext().getAttribute("employeeList");

        DepartmentEntity department = departments.stream()
                .filter(x -> id.equals(x.getId()))
                .findAny()
                .orElse(null);

        List<EmployeeEntity> filteredEmployees = employees.stream()
                .filter(x -> id.equals(x.getDepartmentByDepartmentId().getId()))
                .collect(Collectors.toList());

        if (department == null) {
            errorMessage = "A department with such id doesn't exist";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {

            departments = new RemoveEntityService(departmentDAO).execute(department);

            log.trace("Departments size = " + departments.size());
            log.trace("Employees size = " + employees.size());

            request.getServletContext().setAttribute("departmentList", departments);
            request.getServletContext().setAttribute("employeeList", employees);

            forward = Path.DEPARTMENTS_JSP;
        }


        log.trace("Forward address --> " + forward);
        log.debug("Controller finished, now go to forward address --> " + forward);

        log.debug("Command finished");
        return forward;
    }

    private String addEmployeeAndReturnPath(HttpServletRequest request) {
        log.debug("Command starts");

        String errorMessage = null;
        String forward = Path.ERROR_PAGE;

        String email = request.getParameter("email").trim();

        request.setAttribute("add_mail", email);

        List<EmployeeEntity> employees = (List<EmployeeEntity>) request.getServletContext().getAttribute("employeeList");

        Object object = employees.stream()
                .filter(x -> email.equals(x.getEmail()))
                .findAny()
                .orElse(null);

        if (object == null) {

            EmployeeEntity employee = setUpEmployee(request, employees);
            employee.setEmail(email);

            employees = new AddEntityService(employeeDAO).execute(employee);

            log.trace("Employees size = " + employees.size());
            request.getServletContext().setAttribute("employeeList", employees);

            forward = Path.ADD_EDIT_EMPLOYEE;
        } else {
            errorMessage = "An employee with such email already exists";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }


        log.trace("Forward address --> " + forward);
        log.debug("Controller finished, now go to forward address --> " + forward);

        log.debug("Command finished");
        return forward;
    }

    private EmployeeEntity setUpEmployee(HttpServletRequest request, List<EmployeeEntity> employees) {
        EmployeeEntity employee = new EmployeeEntity();

        //employee.setId(employees.size() + 1);

        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));

        employee.setBirthday(Date.valueOf(request.getParameter("birthday")));
        employee.setJob(request.getParameter("position"));

        DepartmentEntity department = new DepartmentEntity();
        department.setId(Integer.valueOf(request.getParameter("departmentId")));
        employee.setDepartmentByDepartmentId(department);

        employee.setSalary(Double.valueOf(request.getParameter("salary")));

        request.setAttribute("add_first_name", request.getParameter("firstName"));
        request.setAttribute("add_last_name", request.getParameter("lastName"));
        request.setAttribute("add_birth", request.getParameter("birthday"));
        request.setAttribute("add_job", request.getParameter("position"));
        request.setAttribute("add_department_id", request.getParameter("departmentId"));
        request.setAttribute("add_wage", request.getParameter("salary"));

        return employee;
    }

    private String addDepartmentAndReturnPath(HttpServletRequest request) {
        log.debug("Command starts");

        String errorMessage = null;
        String forward = Path.ERROR_PAGE;

        String name = request.getParameter("departmentName").trim();

        List<DepartmentEntity> departments = (List<DepartmentEntity>) request.getServletContext().getAttribute("departmentList");

        Object object = departments.stream()
                .filter(x -> name.equals(x.getOriginalName()))
                .findAny()
                .orElse(null);

        if (object == null) {

            DepartmentEntity department = new DepartmentEntity();
            //department.setId(departments.size() + 1);
            department.setOriginalName(name);

            departments = new AddEntityService(departmentDAO).execute(department);

            log.trace("Departments size = " + departments.size());
            request.getServletContext().setAttribute("departmentList", departments);

            forward = Path.ADD_EDIT_DEPARTMENT;
        } else {
            errorMessage = "A department with such name already exists";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        request.setAttribute("add_name", name);

        log.trace("Forward address --> " + forward);
        log.debug("Controller finished, now go to forward address --> " + forward);

        log.debug("Command finished");
        return forward;
    }
}