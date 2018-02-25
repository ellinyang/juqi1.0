package com.example.juqi.pylon.entity;

import java.util.ArrayList;

/**
 * Created by Lio_Zhuo on 2016/6/2 0002.
 */
public class DefectTypeItem {

    private String firstType;
    private String secondType;
    private ArrayList<String> firstSpinnerList;
    private ArrayList<String> secondSpinnerList;

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public void setFirstSpinnerList(ArrayList<String> firstSpinnerList) {
        this.firstSpinnerList = firstSpinnerList;
    }

    public void setSecondSpinnerList(ArrayList<String> secondSpinnerList) {
        this.secondSpinnerList = secondSpinnerList;
    }

    public String getFirstType() {
        return firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public ArrayList<String> getFirstSpinnerList() {
        return firstSpinnerList;
    }

    public ArrayList<String> getSecondSpinnerList() {
        return secondSpinnerList;
    }
}
