package com.aimprosoft.yesipov.hibernate.web.service;

import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.repository.entity.EmployeeEntity;
import org.apache.log4j.Logger;

import java.util.List;

public class AddEntityService {

    private static final Logger log = Logger.getLogger(AddEntityService.class);

    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;

    public AddEntityService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public AddEntityService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public AddEntityService(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO) {
        this.departmentDAO = departmentDAO;
        this.employeeDAO = employeeDAO;
    }

    public List<DepartmentEntity> execute(DepartmentEntity department) {

        departmentDAO.addDepartment(department);

        List<DepartmentEntity> departments = departmentDAO.departmentsList();

        log.debug("Command finished");

        return departments;
    }

    public List<EmployeeEntity> execute(EmployeeEntity employee) {

        employeeDAO.addEmployee(employee);

        List<EmployeeEntity> employees = employeeDAO.employeeList();

        log.debug("Command finished");

        return employees;
    }
}
