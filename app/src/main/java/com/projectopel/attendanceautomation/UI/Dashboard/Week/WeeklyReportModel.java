package com.projectopel.attendanceautomation.UI.Dashboard.Week;

public class WeeklyReportModel {
    String status ,day, intime, outtime;

    public WeeklyReportModel(String status, String day, String intime, String outtime) {
        this.status = status;
        this.day = day;
        this.intime = intime;
        this.outtime = outtime;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }
}
