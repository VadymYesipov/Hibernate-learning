package com.aimprosoft.yesipov.hibernate.db.dao.impl;

import com.aimprosoft.yesipov.hibernate.db.DBManager;
import com.aimprosoft.yesipov.hibernate.db.EntityMapper;
import com.aimprosoft.yesipov.hibernate.db.Fields;
import com.aimprosoft.yesipov.hibernate.db.Queries;
import com.aimprosoft.yesipov.hibernate.db.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.db.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDepartmentDAO implements DepartmentDAO {

    Connection con;
    PreparedStatement pstmt;
    DBManager dbManager;

    public DBManager getDbManager() throws SQLException {
        return DBManager.getInstance();
    }

    @Override
    public void addDepartment(Department department) {

        try {
            con = getDbManager().getConnection();

            pstmt = con.prepareStatement(Queries.SQL_INSERT_NEW_DEPARTMENT);

            pstmt.setInt(1, department.getId());
            pstmt.setString(2, department.getName());

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
    public void editDepartment(Department department) {

        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            pstmt = con.prepareStatement(Queries.SQL_UPDATE_DEPARTMENT);

            pstmt.setString(1, department.getName());
            pstmt.setInt(2, department.getId());

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
    public void removeDepartment(Department department, int size) {

        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            pstmt = con.prepareStatement(Queries.SQL_DELETE_DEPARTMENT);

            pstmt.setInt(1, department.getId());

            pstmt.execute();

            for (int i = 0; i < size - department.getId(); ) {

                pstmt = con.prepareStatement(Queries.SQL_SHIFT_DEPARTMENT);

                pstmt.setInt(1, department.getId() + i);
                pstmt.setInt(2, department.getId() + ++i);

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
    public List<Department> departmentsList() {

        List<Department> departments = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();

            DepartmentMapper mapper = new DepartmentMapper();

            pstmt = con.prepareStatement(Queries.SQL_FIND_ALL_DEPARTMENTS);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                departments.add(mapper.mapRow(rs));
            }

            rs.close();
            pstmt.close();

        } catch (SQLException ex) {

            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {

            DBManager.getInstance().commitAndClose(con);
        }
        return departments;
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class DepartmentMapper implements EntityMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs) {
            try {
                Department department = new Department();

                department.setId(rs.getInt(Fields.ENTITY__ID));
                department.setName(rs.getString(Fields.DEPARTMENT_NAME));

                return department;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
