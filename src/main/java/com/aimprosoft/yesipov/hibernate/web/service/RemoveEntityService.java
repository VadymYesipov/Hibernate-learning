package com.aimprosoft.yesipov.hibernate.web.service;

import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.repository.entity.EmployeeEntity;
import org.apache.log4j.Logger;

import java.util.List;

public class RemoveEntityService {

    private static final Logger log = Logger.getLogger(RemoveEntityService.class);

    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;

    public RemoveEntityService(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO) {
        this.departmentDAO = departmentDAO;
        this.employeeDAO = employeeDAO;
    }

    public RemoveEntityService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public RemoveEntityService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<DepartmentEntity> execute(DepartmentEntity department) {

        departmentDAO.removeDepartment(department);

        List<DepartmentEntity> departments = departmentDAO.departmentsList();

        log.debug("Command finished");

        return departments;
    }

    public List<EmployeeEntity> execute(EmployeeEntity employee) {

        employeeDAO.removeEmployee(employee);

        List<EmployeeEntity> employees = employeeDAO.filteredEmployeeList(
                employee.getDepartmentByDepartmentId().getId());

        log.debug("Command finished");

        return employees;
    }
}
