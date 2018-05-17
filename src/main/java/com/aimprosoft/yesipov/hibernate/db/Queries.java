package com.aimprosoft.yesipov.hibernate.db;

public final class Queries {

    public static final String SQL_FIND_ALL_DEPARTMENTS = "SELECT * FROM department ORDER BY id";

    public static final String SQL_FIND_ALL_EMPLOYEES = "SELECT employee.id, first_name, last_name, birthday, email, job, department_id, original_name, salary " +
            "FROM employee INNER JOIN department on employee.department_id=department.id;";

    public static final String SQL_FIND_ALL_EMPLOYEES_FILTERED = "SELECT employee.id, first_name, last_name, birthday, email, job, department_id, original_name, salary " +
            "FROM employee INNER JOIN department on employee.department_id=department.id WHERE employee.department_id = ? ;";

    public static final String SQL_INSERT_NEW_DEPARTMENT = "INSERT INTO department (id, original_name) VALUES (?, ?);";

    public static final String SQL_INSERT_NEW_EMPLOYEE = "INSERT INTO employee " +
            "(id, first_name, last_name, birthday, email, job, department_id, salary) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String SQL_DELETE_DEPARTMENT = "DELETE FROM department WHERE id=?;";

    public static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE id=?;";

    public static final String SQL_SHIFT_DEPARTMENT = "UPDATE department SET id = ? WHERE id = ?;";

    public static final String SQL_SHIFT_EMPLOYEE = "UPDATE employee SET id = ? WHERE id = ?;";

    public static final String SQL_UPDATE_DEPARTMENT = "UPDATE department SET original_name = ? WHERE id=?;";

    public static final String SQL_UPDATE_EMPLOYEE = "UPDATE employee SET ";

    public static final String SQL_UPDATE_EMPLOYEE_ID = "UPDATE employee SET id = ? WHERE id = ?";

    public static final String SQL_WHERE = " WHERE id=?;";
}
