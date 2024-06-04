package org.example.dao;

import org.example.dto.EmployeeFilterDto;
import org.example.models.Employees;

import java.sql.*;
import java.util.ArrayList;

public class EmployeesDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\SDAIA-Course-HW\\src\\main\\java\\HW\\day4\\hr.db";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employees";
    private static final String SELECT_ONE_EMPLOYEES = "select * from employees where employee_id = ?";
    private static final String SELECT_EMPLIYEE_WITH_SALARY = "select * from employees where salary = ?";
    private static final String SELECT_EMPLIYEE_WITH_SALARY_PAGINATION = "select * from employees where salary = ? order by employee_id limit ? offset ?";
    private static final String SELECT_EMPLIYEE_WITH_PAGINATION = "select * from employees order by employee_id limit ? offset ?";
    private static final String INSERT_EMPLOYEES = "insert into employees values (?, ?, ?, ?, ?, ?,null, ?,null,null)";
    private static final String UPDATE_EMPLOYEES= "update employees set first_name = ?, last_name = ? ,email = ? ,phone_number = ? ,hire_date = ? ,Salary = ? where employee_id = ?";
    private static final String DELETE_EMPLOYEES = "delete from employees where employees_id = ?";

    public void insertEmployees(Employees e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_EMPLOYEES);
        st.setInt(1, e.getEmployeesId());
        st.setString(2, e.getFirstName());
        st.setString(3, e.getLastName());
        st.setString(4, e.getEmail());
        st.setString(5, e.getPhoneNumber());
        st.setString(6, e.getHireDate());
        st.setDouble(7, e.getSalary());

        st.executeUpdate();
        //conn.close();
    }

    public void updateEmployees(Employees e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_EMPLOYEES);
        st.setString(1, e.getFirstName());
        st.setString(2, e.getLastName());
        st.setString(3, e.getEmail());
        st.setString(4, e.getPhoneNumber());
        st.setString(5, e.getHireDate());
        st.setDouble(6, e.getSalary());
        st.setInt(   7, e.getEmployeesId());

        st.executeUpdate();
        //conn.close();

    }

    public void deleteEmployees(int employeeId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_EMPLOYEES);
        st.setInt(1, employeeId);
        st.executeUpdate();
    }

    public Employees selectEmployees(int employeeId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMPLOYEES);
        st.setInt(1, employeeId);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employees(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Employees> selectAllEmployees(double salary, Integer limit, int offset) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(salary != 0 && limit != null) {
            st = conn.prepareStatement(SELECT_EMPLIYEE_WITH_SALARY_PAGINATION);
            st.setDouble(1, salary);
            st.setInt(2, limit);
            st.setInt(3, offset);
        }
        else if(salary != 0) {
            st = conn.prepareStatement(SELECT_EMPLIYEE_WITH_SALARY);
            st.setDouble(1, salary);
        }
        else if(limit != null) {
            st = conn.prepareStatement(SELECT_EMPLIYEE_WITH_PAGINATION);
            st.setInt(1, limit);
            st.setInt(2, offset);
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_EMPLOYEES);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Employees> employees = new ArrayList<>();
        while (rs.next()) {
            employees.add(new Employees(rs));
        }

        return employees;
    }

    public ArrayList<Employees> selectAllEmployees(EmployeeFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(filter.getSalary() != 0 && filter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_EMPLIYEE_WITH_SALARY_PAGINATION);
            st.setDouble(1, filter.getSalary());
            st.setInt(2, filter.getLimit());
            st.setInt(3, filter.getOffset());
        }
        else if(filter.getSalary() != 0) {
            st = conn.prepareStatement(SELECT_EMPLIYEE_WITH_SALARY);
            st.setDouble(1, filter.getSalary());
        }
        else if(filter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_EMPLIYEE_WITH_PAGINATION);
            st.setInt(1, filter.getLimit());
            st.setInt(2, filter.getOffset());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_EMPLOYEES);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Employees> employees = new ArrayList<>();
        while (rs.next()) {
            employees.add(new Employees(rs));
        }

        return employees;
    }
}