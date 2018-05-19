package com.aimprosoft.yesipov.hibernate.web.service;

import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.repository.entity.EmployeeEntity;
import org.apache.log4j.Logger;

import java.util.List;

public class EditEntityService {

    private static final Logger log = Logger.getLogger(EditEntityService.class);

    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;

    public EditEntityService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public EditEntityService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public EditEntityService(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO) {

        this.departmentDAO = departmentDAO;
        this.employeeDAO = employeeDAO;
    }

    public List<DepartmentEntity> execute(DepartmentEntity department, Integer id) {

        departmentDAO.editDepartment(department, id);

        List<DepartmentEntity> departments = departmentDAO.departmentsList();

        log.debug("Command finished");

        return departments;
    }

    public List<EmployeeEntity> execute(EmployeeEntity employee, Integer id) {

        employeeDAO.editEmployee(employee, id);

        List<EmployeeEntity> employees = employeeDAO.employeeList();

        log.debug("Command finished");

        return employees;
    }
}
