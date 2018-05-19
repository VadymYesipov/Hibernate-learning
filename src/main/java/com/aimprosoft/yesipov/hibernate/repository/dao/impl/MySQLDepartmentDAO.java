package com.aimprosoft.yesipov.hibernate.repository.dao.impl;

import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.repository.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MySQLDepartmentDAO implements DepartmentDAO {

    private static final Logger log = Logger.getLogger(MySQLDepartmentDAO.class);

    private static MySQLDepartmentDAO mySQLDepartmentDAO;

    private MySQLDepartmentDAO() {
    }

    public static MySQLDepartmentDAO getMySQLDepartmentDAO() {
        if (mySQLDepartmentDAO == null) {
            mySQLDepartmentDAO = new MySQLDepartmentDAO();
        }
        return mySQLDepartmentDAO;
    }

    @Override
    public void addDepartment(DepartmentEntity department) {
        Session session = null;

        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            session.save(department);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException e1) {
                log.error("Couldn't roll back transaction", e1);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void editDepartment(DepartmentEntity department, Integer id) {
        log.debug("Editing a department");

        Session session = null;

        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            Query query = session.createQuery("update DepartmentEntity set id = :newId, originalName = :name " +
                    "where id = :id");

            query.setParameter("newId", department.getId());
            query.setParameter("name", department.getOriginalName());
            query.setParameter("id", id);

            int result = query.executeUpdate();
            log.debug("Count of updated departments:" + result);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException e1) {
                log.error("Couldn't roll back transaction", e1);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeDepartment(DepartmentEntity department) {
        Session session = null;

        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            Query query = session.createQuery("delete DepartmentEntity where id = :id");
            query.setParameter("id", department.getId());
            int result = query.executeUpdate();
            log.debug("Count of deleted elements: " + result);

            session.getTransaction().commit();

            session.close();
        } catch (RuntimeException e) {
            try {
                session.getTransaction().rollback();
            } catch (RuntimeException e1) {
                log.error("Couldn't roll back transaction", e1);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<DepartmentEntity> departmentsList() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        Query query = session.createQuery("from DepartmentEntity order by id");
        List<DepartmentEntity> departments = query.list();
        departments.forEach(System.out::println);

        session.getTransaction().commit();

        session.close();

        return departments;
    }
}
