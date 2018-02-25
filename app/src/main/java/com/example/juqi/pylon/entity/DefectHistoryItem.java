package com.example.juqi.pylon.entity;

/**
 * Created by Lio_Zhuo on 2016/7/20 0020.
 */
public class DefectHistoryItem {

    private int id;
    private String lineName;
    private int towerNum;
    private String defectType;
    private String defectLevel;
    private String voltageLevel;
    private String content;
    private String recordTime;
    private String recordPerson;
    private boolean isDel;
    private String delPerson;
    private String delTime;
    private String picUrl;
    private boolean isPMS;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getTowerNum() {
        return towerNum;
    }

    public void setTowerNum(int towerNum) {
        this.towerNum = towerNum;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public String getDefectLevel() {
        return defectLevel;
    }

    public void setDefectLevel(String defectLevel) {
        this.defectLevel = defectLevel;
    }

    public String getVoltageLevel() {
        return voltageLevel;
    }

    public void setVoltageLevel(String voltageLevel) {
        this.voltageLevel = voltageLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getRecordPerson() {
        return recordPerson;
    }

    public void setRecordPerson(String recordPerson) {
        this.recordPerson = recordPerson;
    }

    public boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(boolean isDel) {
        this.isDel = isDel;
    }

    public String getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(String delPerson) {
        this.delPerson = delPerson;
    }

    public boolean getIsPMS() {
        return isPMS;
    }

    public void setIsPMS(boolean isPMS) {
        this.isPMS = isPMS;
    }
}
