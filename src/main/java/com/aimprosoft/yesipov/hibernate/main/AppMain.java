package com.aimprosoft.yesipov.hibernate.main;

import com.aimprosoft.yesipov.hibernate.dao.ContactEntity;
import com.aimprosoft.yesipov.hibernate.repository.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setBirthDate(new java.util.Date());
        contactEntity.setFirstName("Nick");
        contactEntity.setLastName("VN");

        session.save(contactEntity);
        session.getTransaction().commit();

        //session.getSessionFactory().close();



        session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        Query query = session.createQuery("from ContactEntity where firstName = :paramName");
        query.setParameter("paramName", "Nick");
        List list = query.list();
        list.forEach(System.out::println);

        session.getTransaction().commit();
        //session.getSessionFactory().close();




        session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        query = session.createQuery("update ContactEntity set id = 2, firstName = :nameParam, lastName = :lastNameParam" +
                ", birthDate = :birthDateParam"+
                " where firstName = :nameCode");

        query.setParameter("nameCode", "Nick");
        query.setParameter("nameParam", "NickChangedName1");
        query.setParameter("lastNameParam", "LastNameChanged1" );
        query.setParameter("birthDateParam", new Date());

        int result = query.executeUpdate();
        System.out.println(result);

        session.getTransaction().commit();




        session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        contactEntity.setId(1);
        contactEntity.setBirthDate(new java.util.Date());
        contactEntity.setFirstName("Nick");
        contactEntity.setLastName("VN");

        session.save(contactEntity);
        session.getTransaction().commit();



        session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        query =  session.createQuery("delete ContactEntity where firstName = :param");
        query.setParameter("param", "NickChangedName1");
        result = query.executeUpdate();
        System.out.println(result);

        session.getTransaction().commit();

        session.getSessionFactory().close();

    }
}
