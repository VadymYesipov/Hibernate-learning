package com.aimprosoft.yesipov.hibernate.db.dao;

import com.aimprosoft.yesipov.hibernate.db.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee);

    void editEmployee(Employee employee, String query);

    void editEmployee(Integer id, Integer oldId);

    void removeEmployee(Employee employee, int size);

    List<Employee> employeeList();

    List<Employee> filteredEmployeeList(Integer id);
}
