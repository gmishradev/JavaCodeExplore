package com.warehouse;

import java.util.Date;

public class ComputeSchedule {
    private String beId;
    private String acctId;
    private String category;
    private Date statusDate;
    private String computeName;
    private int day;
    private String dayWord;
    private Date startTime;
    private Date endTime;
    private String size;

    // Constructor
    public ComputeSchedule() {
    }

    // Getters and setters
    public String getBeId() {
        return beId;
    }

    public void setBeId(String beId) {
        this.beId = beId;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getComputeName() {
        return computeName;
    }

    public void setComputeName(String computeName) {
        this.computeName = computeName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDayWord() {
        return dayWord;
    }

    public void setDayWord(String dayWord) {
        this.dayWord = dayWord;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
