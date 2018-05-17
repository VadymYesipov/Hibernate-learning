package com.aimprosoft.yesipov.hibernate.web.service;

import com.aimprosoft.yesipov.hibernate.db.entity.Department;
import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.impl.MySQLDepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.impl.MySQLEmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.repository.entity.EmployeeEntity;
import org.apache.log4j.Logger;

import java.util.List;

public class ListService {

    private static final Logger log = Logger.getLogger(ListService.class);

    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;

    public ListService(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO) {
        this.departmentDAO = departmentDAO;
        this.employeeDAO = employeeDAO;
    }

    public List<EmployeeEntity> getFilteredList(Integer id) {

        List<EmployeeEntity> employees = employeeDAO.filteredEmployeeList(id);
        if (employees.size() > 0) {
            log.trace("Employee size = " + employees.size());
        }
        log.debug("Command finished");

        return employees;
    }

    public List<EmployeeEntity> getEmployeeList() {

        List<EmployeeEntity> employees = employeeDAO.employeeList();

        log.trace("Employee size = " + employees.size());

        return employees;
    }

    public List<DepartmentEntity> getDepartmentList() {
        List<DepartmentEntity> departments = MySQLDepartmentDAO.getMySQLDepartmentDAO().departmentsList();

        log.trace("Employee size = " + departments.size());

        return departments;
    }
}
