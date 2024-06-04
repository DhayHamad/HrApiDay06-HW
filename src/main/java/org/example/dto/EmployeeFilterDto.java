package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class EmployeeFilterDto {

    private @QueryParam("salary") double salary;
    private @QueryParam("limit") Integer limit;
    private @QueryParam("offset") int offset;

    public double getSalary() {
        return salary;
    }

    public void setSalary(Integer Salary) {
        this.salary = Salary;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
