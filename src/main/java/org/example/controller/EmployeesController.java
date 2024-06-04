package org.example.controller;

import jakarta.ws.rs.*;
import org.example.dao.EmployeesDAO;
import org.example.models.Employees;

import java.util.ArrayList;

@Path("/employees")
public class EmployeesController {

    EmployeesDAO dao = new EmployeesDAO();

    @GET
    public ArrayList<Employees> selectAllEmployees(){
        try {
            return dao.selectAllEmployees();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{empId}")
    public Employees getEmployees(@PathParam("empId") int empId) {

        try {
            return dao.selectEmployees(empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{empId}")
    public void deleteEmployees(@PathParam("empId") int empId) {

        try {
            dao.deleteEmployees(empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public void insertEmployees(Employees employees) {

        try {
            dao.insertEmployees(employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{empId}")
    public void updateEmployees(@PathParam("empId") int empId,Employees employees) {

        try {
            employees.setEmployeesId(empId);
            dao.updateEmployees(employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}