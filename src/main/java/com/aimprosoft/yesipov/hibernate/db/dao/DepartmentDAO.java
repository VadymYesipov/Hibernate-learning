package com.aimprosoft.yesipov.hibernate.db.dao;

import com.aimprosoft.yesipov.hibernate.db.entity.Department;

import java.util.List;

public interface DepartmentDAO {

    void addDepartment(Department department);

    void editDepartment(Department department);

    void removeDepartment(Department department, int size);

    List<Department> departmentsList();
}
