package com.aimprosoft.yesipov.hibernate.web.service;

import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import org.apache.log4j.Logger;

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
}
