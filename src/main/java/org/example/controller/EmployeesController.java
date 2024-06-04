package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dao.EmployeesDAO;
import org.example.dto.EmployeeFilterDto;
import org.example.models.Employees;

import java.util.ArrayList;

@Path("/employees")
public class EmployeesController {

    EmployeesDAO dao = new EmployeesDAO();

    @GET
    @Produces ({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public ArrayList<Employees> selectAllEmployees(
          // @QueryParam("salary") Integer salary,
          // @QueryParam("limit") Integer limit,
          // @QueryParam("offset") int offset
          @BeanParam EmployeeFilterDto filter
    ){
        try {
            return dao.selectAllEmployees(filter);
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
   /* @GET
    public ArrayList<Employees> getAllEmployees(@PathParam("empId") Integer empId) {
        System.out.println(empId);
        ArrayList<Employees> employees = new ArrayList<>();
        employees.add(new Employees());
        employees.add(new Employees());
        employees.add(new Employees());
        employees.add(new Employees());
        return employees;
    }

    @POST
    public void insertEmployee(@PathParam("empId") Integer empId, Employees emp) {

        System.out.println(empId);
        System.out.println(emp);
    }
*/
}
