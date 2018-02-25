package com.example.juqi.pylon.chart;
/**
 * 结点
 * @author tomkeyzhang（qitongzhang@anjuke.com）
 * @date :2014年4月17日
 */
public class Point {
    /**是否在图形中绘制出此结点*/
    public boolean willDrawing;
    /**是否画小塔*/
    public boolean isChosen = false;
    /** 在canvas中的X坐标 */
    public float x;
    /** 在canvas中的Y坐标 */
    public float y;
    /** 实际的X数值 */
    public int valueX;
    /** 实际的Y数值 */
    public float valueY;
    /** 线路名 */
    public String lineName;
    /** 塔号 */
    public int towerNum;
    /** 经度、纬度、海拔 */
    public float longitude;
    public float latitude;
    public float altitude;
    public Point() {
	}
    public Point(int valueX, float valueY,boolean willDrawing) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.willDrawing=willDrawing;
    }
    public Point(int valueX, float valueY,float longitude,float latitude,float altitude,boolean willDrawing) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.willDrawing=willDrawing;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public void setChosen(boolean willDrawTower){
        this.isChosen = willDrawTower;
    }
    
}
