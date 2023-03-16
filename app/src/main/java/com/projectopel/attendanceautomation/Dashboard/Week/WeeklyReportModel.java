package com.projectopel.attendanceautomation.Dashboard.Week;

public class WeeklyReportModel {
    String img ,day, intime, outtime;

    public WeeklyReportModel(String img, String day, String intime, String outtime) {
        this.img = img;
        this.day = day;
        this.intime = intime;
        this.outtime = outtime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
