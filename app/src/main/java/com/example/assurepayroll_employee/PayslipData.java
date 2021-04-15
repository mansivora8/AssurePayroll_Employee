package com.example.assurepayroll_employee;

public class PayslipData {
    private String pid;
   private String empId;
    private String pay_date;
    private int salaryPM;
    private int full;
    private int half;
    private int sick;
    private int dfull;
    private int dhalf;
    private int dsick;
    private int totalDeduction;
    private int totalSalary;
    private String name;
    public PayslipData() {
    }

    public PayslipData(String pid, String pay_date, int salaryPM, int full, int half, int sick, int dfull, int dhalf, int dsick, int totalDeduction, int totalSalary, String name) {
        this.pid = pid;
        this.empId = empId;
        this.pay_date = pay_date;
        this.salaryPM = salaryPM;
        this.full = full;
        this.half = half;
        this.sick = sick;
        this.dfull = dfull;
        this.dhalf = dhalf;
        this.dsick = dsick;
        this.totalDeduction = totalDeduction;
        this.totalSalary = totalSalary;
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public int getSalaryPM() {
        return salaryPM;
    }

    public void setSalaryPM(int salaryPM) {
        this.salaryPM = salaryPM;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public int getHalf() {
        return half;
    }

    public void setHalf(int half) {
        this.half = half;
    }

    public int getSick() {
        return sick;
    }

    public void setSick(int sick) {
        this.sick = sick;
    }

    public int getDfull() {
        return dfull;
    }

    public void setDfull(int dfull) {
        this.dfull = dfull;
    }

    public int getDhalf() {
        return dhalf;
    }

    public void setDhalf(int dhalf) {
        this.dhalf = dhalf;
    }

    public int getDsick() {
        return dsick;
    }

    public void setDsick(int dsick) {
        this.dsick = dsick;
    }

    public int getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(int totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
