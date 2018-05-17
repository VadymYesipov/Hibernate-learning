package com.aimprosoft.yesipov.hibernate.repository.dao;

import com.aimprosoft.yesipov.hibernate.repository.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO {

    void addEmployee(EmployeeEntity employee);

    void editEmployee(EmployeeEntity employee);

    void removeEmployee(EmployeeEntity employee);

    List<EmployeeEntity> employeeList();

    List<EmployeeEntity> filteredEmployeeList(Integer id);
}
