package com.aimprosoft.yesipov.hibernate.repository.dao.impl;

import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.repository.entity.EmployeeEntity;
import com.aimprosoft.yesipov.hibernate.repository.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MySQLEmployeeDAO implements EmployeeDAO {

    private static final Logger log = Logger.getLogger(MySQLEmployeeDAO.class);

    private static MySQLEmployeeDAO mySQLEmployeeDAO;

    private MySQLEmployeeDAO() {
    }

    public static MySQLEmployeeDAO getMySQLEmployeeDAO() {
        if (mySQLEmployeeDAO == null) {
            mySQLEmployeeDAO = new MySQLEmployeeDAO();
        }
        return mySQLEmployeeDAO;
    }

    @Override
    public void addEmployee(EmployeeEntity employee) {
        log.debug("Adding an employee");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(employee);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void editEmployee(EmployeeEntity employee) {

    }

    @Override
    public void removeEmployee(EmployeeEntity employee) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        Query query =  session.createQuery("delete EmployeeEntity where id = :id");
        query.setParameter("id", employee.getId());
        int result = query.executeUpdate();
        log.debug("Count of deleted elements: " + result);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<EmployeeEntity> employeeList() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        Query query = session.createQuery("from EmployeeEntity ");
        List<EmployeeEntity> employees = query.list();
        employees.forEach(System.out::println);

        session.getTransaction().commit();

        session.close();

        return employees;
    }

    @Override
    public List<EmployeeEntity> filteredEmployeeList(Integer id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        Query query = session.createQuery("from EmployeeEntity where departmentByDepartmentId.id = :id");
        query.setParameter("id", id);

        List<EmployeeEntity> employees = query.list();
        employees.forEach(System.out::println);

        session.getTransaction().commit();

        session.close();

        return employees;
    }
}
