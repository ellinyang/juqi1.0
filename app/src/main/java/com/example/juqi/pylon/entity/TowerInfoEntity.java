package com.example.juqi.pylon.entity;

import java.io.Serializable;

/**
 * Created by Lio_Zhuo on 2016/5/31 0031.
 */
public class TowerInfoEntity implements Serializable {

    private int id;
    private String lineName;
    private int towerNum;
    private String useDate;
    private double lineDis;
    private int towerCount;
    private String towerType;
    private String phaseAlign;
    private String span;
    private String towerModel;
    private String towerFactory;
    private double towerHeight;
    private String wireType;
    private String wireFactory;
    private String jumperType;
    private String jumperFactory;
    private String opticalCableType;
    private String opticalCableFactory;
    private String shockProofType;
    private String shockProofFactory;
    private String ironwareFactory;
    private int inspectionPeriod;
    private String lastTime;
//    private float longitude;
//    private float latitude;
//    private float altitude;
    private float[] lineTowersAltitude;

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

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public double getLineDis() {
        return lineDis;
    }

    public void setLineDis(double lineDis) {
        this.lineDis = lineDis;
    }

    public int getTowerCount() {
        return towerCount;
    }

    public void setTowerCount(int towerCount) {
        this.towerCount = towerCount;
    }

    public String getTowerType() {
        return towerType;
    }

    public void setTowerType(String towerType) {
        this.towerType = towerType;
    }

    public String getPhaseAlign() {
        return phaseAlign;
    }

    public void setPhaseAlign(String phaseAlign) {
        this.phaseAlign = phaseAlign;
    }

    public String getSpan() {
        return span;
    }

    public void setSpan(String span) {
        this.span = span;
    }

    public String getTowerModel() {
        return towerModel;
    }

    public void setTowerModel(String towerModel) {
        this.towerModel = towerModel;
    }

    public String getTowerFactory() {
        return towerFactory;
    }

    public void setTowerFactory(String towerFactory) {
        this.towerFactory = towerFactory;
    }

    public double getTowerHeight() {
        return towerHeight;
    }

    public void setTowerHeight(double towerHeight) {
        this.towerHeight = towerHeight;
    }

    public String getWireType() {
        return wireType;
    }

    public void setWireType(String wireType) {
        this.wireType = wireType;
    }

    public String getWireFactory() {
        return wireFactory;
    }

    public void setWireFactory(String wireFactory) {
        this.wireFactory = wireFactory;
    }

    public String getJumperType() {
        return jumperType;
    }

    public void setJumperType(String jumperType) {
        this.jumperType = jumperType;
    }

    public String getJumperFactory() {
        return jumperFactory;
    }

    public void setJumperFactory(String jumperFactory) {
        this.jumperFactory = jumperFactory;
    }

    public String getOpticalCableType() {
        return opticalCableType;
    }

    public void setOpticalCableType(String opticalCableType) {
        this.opticalCableType = opticalCableType;
    }

    public String getOpticalCableFactory() {
        return opticalCableFactory;
    }

    public void setOpticalCableFactory(String opticalCableFactory) {
        this.opticalCableFactory = opticalCableFactory;
    }

    public String getShockProofType() {
        return shockProofType;
    }

    public void setShockProofType(String shockProofType) {
        this.shockProofType = shockProofType;
    }

    public String getShockProofFactory() {
        return shockProofFactory;
    }

    public void setShockProofFactory(String shockProofFactory) {
        this.shockProofFactory = shockProofFactory;
    }

    public String getIronwareFactory() {
        return ironwareFactory;
    }

    public void setIronwareFactory(String ironwareFactory) {
        this.ironwareFactory = ironwareFactory;
    }

    public int getInspectionPeriod() {
        return inspectionPeriod;
    }

    public void setInspectionPeriod(int inspectionPeriod) {
        this.inspectionPeriod = inspectionPeriod;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

//    public float getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(float longitude) {
//        this.longitude = longitude;
//    }
//
//    public float getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(float latitude) {
//        this.latitude = latitude;
//    }
//
//    public float getAltitude() {
//        return altitude;
//    }
//
//    public void setAltitude(float altitude) {
//        this.altitude = altitude;
//    }

    public float[] getLineTowersAltitude() {
        return lineTowersAltitude;
    }

    public void setLineTowersAltitude(float[] lineTowersAltitude) {
        this.lineTowersAltitude = lineTowersAltitude;
    }
}
