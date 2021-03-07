package com.example.assurepayroll_employee;

public class LeavesData {
    private String id;
    private String date;
    private String reson;
    private String leave_type_id;
    private String emp_id,status;

    public LeavesData() {
    }

    public LeavesData(String id, String date, String reson, String leave_type_id, String emp_id, String status) {
        this.id = id;
        this.date = date;
        this.reson = reson;
        this.leave_type_id = leave_type_id;
        this.emp_id = emp_id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getLeave_type_id() {
        return leave_type_id;
    }

    public void setLeave_type_id(String leave_type_id) {
        this.leave_type_id = leave_type_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
