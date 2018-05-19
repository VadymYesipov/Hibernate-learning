package com.aimprosoft.yesipov.hibernate.repository.dao;

import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentDAO {

    void addDepartment(DepartmentEntity department);

    void editDepartment(DepartmentEntity department, Integer id);

    List<DepartmentEntity> departmentsList();

    void removeDepartment(DepartmentEntity department);
}
