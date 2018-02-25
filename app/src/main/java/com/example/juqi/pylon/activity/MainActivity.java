package com.example.juqi.pylon.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juqi.pylon.MyApplication;
import com.example.juqi.pylon.R;
import com.example.juqi.pylon.adapter.TowerInfoAdapter;
import com.example.juqi.pylon.entity.TowerInfoEntity;
import com.example.juqi.pylon.entity.TowerInfoItem;
import com.example.juqi.pylon.chart.BesselChart;
import com.example.juqi.pylon.chart.ChartData;
import com.example.juqi.pylon.chart.Point;
import com.example.juqi.pylon.chart.Series;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends TitleActivity {

    private TowerInfoEntity towerInfo;
    private BesselChart chart;
    private List<Point> points = new ArrayList<Point>();
    private Handler handler = new Handler();
    private Button defectTypeButton;
    private Button defectHistoryButton;
    private ImageView imageView;
    private List<TowerInfoItem> towerInfoList = new ArrayList<TowerInfoItem>();
    private ListView towerInfoListView;
    private TowerInfoAdapter towerInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        setTitle("塔杆信息");
        Gson gson = new Gson();
        towerInfo = gson.fromJson("{\"id\":1,\"lineName\":\"六晓5410线\",\"towerNum\":1,\"useDate\":\"2014-01-24\",\"lineDis\":17.502,\"towerCount\":31,\"towerType\":\"耐张\",\"phaseAlign\":\"ABC（垂直排列）\",\"span\":\"49/360\",\"towerModel\":\"5K4-SDJC-27\",\"towerFactory\":\"常熟风范\",\"towerHeight\":57.9,\"wireType\":\"JL/LB20A-800/55\",\"wireFactory\":\"杭州电缆厂\",\"jumperType\":\"JL/LB20A-800/55\",\"jumperFactory\":\"杭州电缆厂\",\"opticalCableType\":\"OPGW-14-11-2\",\"opticalCableFactory\":\"江苏宏图高科技股份有限公司\",\"shockProofType\":\"FDN-40L\",\"shockProofFactory\":\"南京线路器材厂\",\"ironwareFactory\":\"南京线路器材厂\",\"inspectionPeriod\":30,\"lastTime\":\"2016-6-16\",\"lineTowersAltitude\":[3,2,84,136,120,126,106,243,249,140,188,124,33,115,73,174,179,113,120,121,112,120,15,8,15,88,134,99,33,40,28]}",TowerInfoEntity.class);
        String timeStamp=towerInfo.getLastTime();
        //获取上次巡视时间
//        if(towerInfo.getLastTime() !=null&&towerInfo.getLastTime() !=""){
        //long timeStamp = Long.parseLong(towerInfo.getLastTime());
//        }
//
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date lastInspectionDate= null;
        try {
            lastInspectionDate = format.parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("MainActivity", lastInspectionDate.toString());
        String lastInspectionTimeStr = format.format(lastInspectionDate);

        Log.d("MainActivity", lastInspectionTimeStr);
        //获取下个巡视时间
        Calendar lastTime = Calendar.getInstance();
        lastTime.setTime(lastInspectionDate);
        lastTime.add(Calendar.DATE,towerInfo.getInspectionPeriod());
        Date date = lastTime.getTime();
        String nextInspectionDate = format.format(date);
        setTowerInfo(towerInfo,lastInspectionTimeStr,nextInspectionDate);
        Log.d("MainActivity", "lastTime"+towerInfo.getLastTime());
        Log.d("MainActivity", "LineName"+towerInfo.getLineName());
        setChart(towerInfo);//绘制曲线

        //设置按钮点击事件
        defectTypeButton = (Button)findViewById(R.id.defect_type_button);
        defectTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DefectTypeActivity.class);
                intent.putExtra("tower_info",towerInfo);
                startActivity(intent);
            }
        });

        //设置按钮点击事件
        defectHistoryButton = (Button)findViewById(R.id.defect_history_button);
        defectHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DefectHistoryInquireActivity.class);
                intent.putExtra("tower_info",towerInfo);
                startActivity(intent);
            }
        });

    }

    /** 绘制曲线 */
    public void setChart(final TowerInfoEntity towerInfo) {
        chart = (BesselChart) findViewById(R.id.chart);
        chart.setSmoothness(0.4f);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                chart.setData(getChartData(true));
                getSeriesList(true,towerInfo);
                // chart.setDrawBesselPoint(true);
                chart.setSmoothness(0.33f);
            }
        }, 1000);

    }

    /** 获取杆塔海拔数据。设置曲线的点 */
    private Series getSeries(String title, int color, boolean willDrawing, TowerInfoEntity towerInfo) {
        Random random = new Random();
        if (willDrawing) {
            float[] lineTowersAltitude = towerInfo.getLineTowersAltitude();
            for (int i = 0; i < lineTowersAltitude.length; i++) {
                points.add(new Point(i + 1, lineTowersAltitude[i], true));
            }
            /** 设置本电塔 */
            points.get(towerInfo.getTowerNum()-1).setChosen(true);
        }
        for (Point point : points) {
            Log.d("bessel","getRandomSeries valueY=" + point.valueY);
        }
        return new Series(title, color, points);
    }


    private void getSeriesList(boolean willDrawing,TowerInfoEntity towerInfo) {
        List<Series> series = new ArrayList<Series>();
        series.add(getSeries("", Color.LTGRAY, willDrawing,towerInfo));
        if (willDrawing) {
            chart.getData().setLabelTransform(new ChartData.LabelTransform() {
                @Override
                public String verticalTransform(int valueY) {
                    return String.format("%dm", valueY);
                }
                @Override
                public String horizontalTransform(int valueX) {
                    return String.format("%d#塔",valueX);
                }
                @Override
                public boolean labelDrawing(int valueX) {
                    return true;
                }
            });
        }
        chart.getData().setSeriesList(series);
        chart.refresh(true);

    }

    private void setTowerInfo(final TowerInfoEntity towerInfo, String lastInspectionDate , String nextInspectionDate) {

        TowerInfoItem towerInfoItem0 = new TowerInfoItem("投运日期",towerInfo.getUseDate().trim());
        towerInfoList.add(towerInfoItem0);
        TowerInfoItem towerInfoItem1 = new TowerInfoItem("线路全长", Double.toString(towerInfo.getLineDis()).trim()+"km");
        towerInfoList.add(towerInfoItem1);
        TowerInfoItem towerInfoItem2 = new TowerInfoItem("杆塔总数", Integer.toString(towerInfo.getTowerCount()).trim()+"基");
        towerInfoList.add(towerInfoItem2);
        TowerInfoItem towerInfoItem3 = new TowerInfoItem("杆塔类别",towerInfo.getTowerType().trim());
        towerInfoList.add(towerInfoItem3);
        TowerInfoItem towerInfoItem4 = new TowerInfoItem("相位排列",towerInfo.getPhaseAlign().trim());
        towerInfoList.add(towerInfoItem4);
        TowerInfoItem towerInfoItem5 = new TowerInfoItem("档距(小号/大号)",towerInfo.getSpan().trim());
        towerInfoList.add(towerInfoItem5);
        TowerInfoItem towerInfoItem6 = new TowerInfoItem("杆塔型号",towerInfo.getTowerModel().trim());
        towerInfoList.add(towerInfoItem6);
        TowerInfoItem towerInfoItem7 = new TowerInfoItem("杆塔厂家",towerInfo.getTowerFactory().trim());
        towerInfoList.add(towerInfoItem7);
        TowerInfoItem towerInfoItem8 = new TowerInfoItem("杆塔全高", Double.toString(towerInfo.getTowerHeight()).trim() + "m");
        towerInfoList.add(towerInfoItem8);
        TowerInfoItem towerInfoItem9 = new TowerInfoItem("导线型号",towerInfo.getWireType().trim());
        towerInfoList.add(towerInfoItem9);
        TowerInfoItem towerInfoItem10 = new TowerInfoItem("导线厂家",towerInfo.getWireFactory().trim());
        towerInfoList.add(towerInfoItem10);
        TowerInfoItem towerInfoItem11 = new TowerInfoItem("跳线型号",towerInfo.getJumperType().trim());
        towerInfoList.add(towerInfoItem11);
        TowerInfoItem towerInfoItem12 = new TowerInfoItem("跳线厂家",towerInfo.getJumperFactory().trim());
        towerInfoList.add(towerInfoItem12);
        TowerInfoItem towerInfoItem13 = new TowerInfoItem("光缆型号",towerInfo.getOpticalCableType().trim());
        towerInfoList.add(towerInfoItem13);
        TowerInfoItem towerInfoItem14 = new TowerInfoItem("光缆厂家",towerInfo.getOpticalCableFactory().trim());
        towerInfoList.add(towerInfoItem14);
        TowerInfoItem towerInfoItem15 = new TowerInfoItem("导线防震锤型号",towerInfo.getShockProofType().trim());
        towerInfoList.add(towerInfoItem15);
        TowerInfoItem towerInfoItem16 = new TowerInfoItem("导线防震锤厂家",towerInfo.getShockProofFactory().trim());
        towerInfoList.add(towerInfoItem16);
        TowerInfoItem towerInfoItem17 = new TowerInfoItem("金具厂家",towerInfo.getIronwareFactory().trim());
        towerInfoList.add(towerInfoItem17);
        TowerInfoItem towerInfoItem18 = new TowerInfoItem("巡视周期", Integer.toString(towerInfo.getInspectionPeriod()).trim() + "天");
        towerInfoList.add(towerInfoItem18);
        TowerInfoItem towerInfoItem19 = new TowerInfoItem("上次巡视时间",lastInspectionDate);
        towerInfoList.add(towerInfoItem19);
        TowerInfoItem towerInfoItem20 = new TowerInfoItem("下次巡视时间",nextInspectionDate);
        towerInfoList.add(towerInfoItem20);

        towerInfoAdapter = new TowerInfoAdapter(MainActivity.this,towerInfoList);
        towerInfoListView = (ListView)findViewById(R.id.tower_info_list_view);
        towerInfoListView.setAdapter(towerInfoAdapter);

    }


    /*监听返回键，弹出退出对话框*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出本次巡检吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener2);
            isExit.setButton2("取消", listener2);
            // 显示对话框
            isExit.show();
        }
        return false;
    }

    /**监听对话框里面的button点击事件*/
    DialogInterface.OnClickListener listener2 = new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroy(){
        MyApplication.getInstance().removeActivity(this);
        super.onDestroy();
    }

}
