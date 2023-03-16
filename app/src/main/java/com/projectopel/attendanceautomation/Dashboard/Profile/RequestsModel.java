package com.projectopel.attendanceautomation.Dashboard.Profile;

public class RequestsModel {

    String id,sr_no,status,from_date,to_date,reason,reason_details,reason_document_url;

    public RequestsModel(String id, String sr_no, String status, String from_date, String to_date, String reason, String reason_details, String reason_document_url) {
        this.id = id;
        this.sr_no = sr_no;
        this.status = status;
        this.from_date = from_date;
        this.to_date = to_date;
        this.reason = reason;
        this.reason_details = reason_details;
        this.reason_document_url = reason_document_url;
    }

    public RequestsModel(String id, String sr_no, String status, String from_date, String to_date, String reason) {
        this.id = id;
        this.sr_no = sr_no;
        this.status = status;
        this.from_date = from_date;
        this.to_date = to_date;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSr_no() {
        return sr_no;
    }

    public void setSr_no(String sr_no) {
        this.sr_no = sr_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason_details() {
        return reason_details;
    }

    public void setReason_details(String reason_details) {
        this.reason_details = reason_details;
    }

    public String getReason_document_url() {
        return reason_document_url;
    }

    public void setReason_document_url(String reason_document_url) {
        this.reason_document_url = reason_document_url;
    }
}
