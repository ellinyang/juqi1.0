
package com.example.juqi.pylon.entity;

/**
 * Created by Lio_Zhuo on 2016/5/31 0031.
 */
public class TowerInfoItem {

    private String towerInfoName;
    private String towerInfoValue;

    public TowerInfoItem(String towerInfoName, String towerInfoValue){
        this.towerInfoName = towerInfoName;
        this.towerInfoValue = towerInfoValue;
    }

    public String getTowerInfoName(){
        return towerInfoName;
    }

    public String getTowerInfoValue(){
        return towerInfoValue;
    }

}
