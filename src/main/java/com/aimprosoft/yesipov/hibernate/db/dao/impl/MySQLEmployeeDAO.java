package com.aimprosoft.yesipov.hibernate.db.dao.impl;

import com.aimprosoft.yesipov.hibernate.db.DBManager;
import com.aimprosoft.yesipov.hibernate.db.EntityMapper;
import com.aimprosoft.yesipov.hibernate.db.Fields;
import com.aimprosoft.yesipov.hibernate.db.Queries;
import com.aimprosoft.yesipov.hibernate.db.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.db.entity.Department;
import com.aimprosoft.yesipov.hibernate.db.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmployeeDAO implements EmployeeDAO {

    @Override
    public void addEmployee(Employee employee) {

        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            pstmt = con.prepareStatement(Queries.SQL_INSERT_NEW_EMPLOYEE);

            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getLastName());
            pstmt.setDate(4, employee.getBirthday());
            pstmt.setString(5, employee.getEmail());
            pstmt.setString(6, employee.getPosition());
            pstmt.setInt(7, employee.getDepartment().getId());
            pstmt.setDouble(8, employee.getSalary());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException ex) {

            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {

            DBManager.getInstance().commitAndClose(con);
        }
    }

    @Override
    public void editEmployee(Integer id, Integer oldId) {

        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            pstmt = con.prepareStatement(Queries.SQL_UPDATE_EMPLOYEE_ID);

            pstmt.setInt(1, id);
            pstmt.setInt(2, oldId);

            pstmt.execute();

            pstmt.close();

        } catch (SQLException ex) {

            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {

            DBManager.getInstance().commitAndClose(con);
        }
    }

    @Override
    public void editEmployee(Employee employee, String query) {

        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            pstmt = con.prepareStatement(Queries.SQL_UPDATE_EMPLOYEE + query + Queries.SQL_WHERE);

            pstmt.setInt(1, employee.getId());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException ex) {

            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {

            DBManager.getInstance().commitAndClose(con);
        }
    }

    @Override
    public void removeEmployee(Employee employee, int size) {

        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            pstmt = con.prepareStatement(Queries.SQL_DELETE_EMPLOYEE);

            pstmt.setInt(1, employee.getId());

            pstmt.execute();

            for (int i = 0; i < size - employee.getId(); ) {

                pstmt = con.prepareStatement(Queries.SQL_SHIFT_EMPLOYEE);

                pstmt.setInt(1, employee.getId() + i);
                pstmt.setInt(2, employee.getId() + ++i);

                pstmt.execute();
            }

            pstmt.close();

        } catch (SQLException ex) {

            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {

            DBManager.getInstance().commitAndClose(con);
        }
    }

    @Override
    public List<Employee> employeeList() {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EmployeeMapper mapper = new EmployeeMapper();
            pstmt = con.prepareStatement(Queries.SQL_FIND_ALL_EMPLOYEES);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                employees.add(mapper.mapRow(rs));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return employees;
    }

    @Override
    public List<Employee> filteredEmployeeList(Integer id) {

        List<Employee> employees = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            EmployeeMapper mapper = new EmployeeMapper();

            pstmt = con.prepareStatement(Queries.SQL_FIND_ALL_EMPLOYEES_FILTERED);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            int i = 1;
            while (rs.next()) {

                Employee employee = mapper.mapRow(rs);
                employee.setId(i++);

                employees.add(employee);
            }

            rs.close();
            pstmt.close();

        } catch (SQLException ex) {

            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {

            DBManager.getInstance().commitAndClose(con);
        }
        return employees;
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class EmployeeMapper implements EntityMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs) {
            try {
                Employee employee = new Employee();

                employee.setId(rs.getInt(Fields.ENTITY__ID));

                employee.setFirstName(rs.getString(Fields.EMPLOYEE_FIRST_NAME));
                employee.setLastName(rs.getString(Fields.EMPLOYEE_LAST_NAME));

                employee.setBirthday(rs.getDate(Fields.EMPLOYEE_BIRTHDAY));

                employee.setEmail(rs.getString(Fields.EMPLOYEE_EMAIL));

                employee.setPosition(rs.getString(Fields.EMPLOYEE_POSITION));

                Department department = new Department();
                department.setId(rs.getInt(Fields.DEPARTMENT_ID));
                department.setName(rs.getString(Fields.DEPARTMENT_NAME));
                employee.setDepartment(department);


                employee.setSalary(rs.getDouble(Fields.EMPLOYEE_SALARY));

                return employee;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
