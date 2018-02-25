package com.example.juqi.pylon.activity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juqi.pylon.entity.DefectTypeItem;
import com.example.juqi.pylon.MyApplication;
import com.example.juqi.pylon.R;
import com.example.juqi.pylon.entity.TowerInfoEntity;
import com.example.juqi.pylon.MyApplication;
import com.example.juqi.pylon.adapter.DefectTypeAdapter;
import com.example.juqi.pylon.entity.DefectTypeItem;

import java.util.ArrayList;
import java.util.List;

public class DefectTypeActivity extends TitleActivity {

    private TowerInfoEntity towerInfo;
    private List<DefectTypeItem> defectTypeList = new ArrayList<>();
    private ListView defectTypeListView;
    private DefectTypeAdapter defectTypeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_defect_type);
        Log.d("title","First setContentView");
        towerInfo = (TowerInfoEntity)getIntent().getSerializableExtra("tower_info");
        //textView =(TextView)findViewById(R.id.activity_defect_type_title);
        //textView.setText("巡检项目");
        setTitle("巡检项目");
        Log.d("title","First SetTitle");
        setDefectType();
        Log.d("title","First setDefectType");
    }

    private void setDefectType() {
        DefectTypeItem defectTypeItem0 = new DefectTypeItem();
        defectTypeItem0.setFirstType("基础");
        ArrayList<String> firstSpinnerList0 = new ArrayList<>();
        firstSpinnerList0.add("基础表面破损");
        firstSpinnerList0.add("基础表面有裂纹");
        firstSpinnerList0.add("基础周围地基沉降");
        firstSpinnerList0.add("基础周围有杂物堆积");
        firstSpinnerList0.add("基础周围有积水");
        defectTypeItem0.setFirstSpinnerList(firstSpinnerList0);

        defectTypeItem0.setSecondType("杆塔");
        ArrayList<String> secondSpinnerList0 = new ArrayList<>();
        secondSpinnerList0.add("杆塔倾斜");
        secondSpinnerList0.add("杆塔塔材锈蚀");
        secondSpinnerList0.add("杆塔塔材缺失");
        secondSpinnerList0.add("杆塔塔材变形");
        defectTypeItem0.setSecondSpinnerList(secondSpinnerList0);
        defectTypeList.add(defectTypeItem0);

        DefectTypeItem defectTypeItem1 = new DefectTypeItem();
        defectTypeItem1.setFirstType("导地线");
        ArrayList<String> firstSpinnerList1 = new ArrayList<>();
        firstSpinnerList1.add("导地线有断股现象");
        firstSpinnerList1.add("导地线有锈蚀现象");
        firstSpinnerList1.add("导地线上有异物悬挂");
        defectTypeItem1.setFirstSpinnerList(firstSpinnerList1);

        defectTypeItem1.setSecondType("绝缘子");
        ArrayList<String> secondSpinnerList1 = new ArrayList<>();
        secondSpinnerList1.add("复合绝缘子老化");
        secondSpinnerList1.add("绝缘子表面脏污");
        secondSpinnerList1.add("玻璃绝缘子自爆");
        defectTypeItem1.setSecondSpinnerList(secondSpinnerList1);
        defectTypeList.add(defectTypeItem1);

        DefectTypeItem defectTypeItem2 = new DefectTypeItem();
        defectTypeItem2.setFirstType("金具");
        ArrayList<String> firstSpinnerList2 = new ArrayList<>();
        firstSpinnerList2.add("金具有磨损");
        firstSpinnerList2.add("金具有锈蚀");
        firstSpinnerList2.add("防振锤有滑移");
        firstSpinnerList2.add("防振锤掉落");
        defectTypeItem2.setFirstSpinnerList(firstSpinnerList2);

        defectTypeItem2.setSecondType("防雷和接地装置");
        ArrayList<String> secondSpinnerList2 = new ArrayList<>();
        secondSpinnerList2.add("接地电阻偏大");
        secondSpinnerList2.add("接地引下线锈蚀");
        secondSpinnerList2.add("接地引下线断开");
        secondSpinnerList2.add("接地引下线外露");
        secondSpinnerList2.add("避雷器与计数器引线脱开");
        secondSpinnerList2.add("避雷器电极板偏转");
        secondSpinnerList2.add("避雷器与绝缘子串脱开");
        secondSpinnerList2.add("避雷器与导线连接处脱开");
        defectTypeItem2.setSecondSpinnerList(secondSpinnerList2);
        defectTypeList.add(defectTypeItem2);

        DefectTypeItem defectTypeItem3 = new DefectTypeItem();
        defectTypeItem3.setFirstType("附属设施");
        ArrayList<String> firstSpinnerList3 = new ArrayList<>();
        firstSpinnerList3.add("线路标识牌缺失");
        firstSpinnerList3.add("线路标识牌褪色");
        firstSpinnerList3.add("在线监测装置连接线缆散落");
        firstSpinnerList3.add("在线监测装置太阳能板损坏");
        firstSpinnerList3.add("在线监测装置损坏");
        firstSpinnerList3.add("航空警示灯损坏");
        firstSpinnerList3.add("航空警示漆褪色");
        firstSpinnerList3.add("杆塔上有鸟巢");
        defectTypeItem3.setFirstSpinnerList(firstSpinnerList3);

        defectTypeItem3.setSecondType("保护区及线路通道");
        ArrayList<String> secondSpinnerList3 = new ArrayList<>();
        secondSpinnerList3.add("交跨距离不满足要求");
        secondSpinnerList3.add("线路保护区内有临时构筑物");
        secondSpinnerList3.add("线路保护区内有施工作业");
        secondSpinnerList3.add("杆塔周围有杂物堆积");
        secondSpinnerList3.add("线路附近有易垂钓区");
        secondSpinnerList3.add("线路附近有易发生山火区段");
        secondSpinnerList3.add("线路附近有大型污染源");
        defectTypeItem3.setSecondSpinnerList(secondSpinnerList3);
        defectTypeList.add(defectTypeItem3);


        DefectTypeItem defectTypeItem4 = new DefectTypeItem();
        defectTypeItem4.setFirstType("电缆本体");
        defectTypeItem4.setSecondType("电缆终端");
        defectTypeList.add(defectTypeItem4);

        DefectTypeItem defectTypeItem5 = new DefectTypeItem();
        defectTypeItem5.setFirstType("辅助设备");
        defectTypeItem5.setSecondType("中间接头");

        defectTypeList.add(defectTypeItem5);

        DefectTypeItem defectTypeItem6 = new DefectTypeItem();
        defectTypeItem6.setFirstType("电缆线路避雷器");
        defectTypeItem6.setSecondType("通道");
        defectTypeList.add(defectTypeItem6);

        defectTypeAdapter = new DefectTypeAdapter(DefectTypeActivity.this,defectTypeList,towerInfo);
        defectTypeListView = (ListView)findViewById(R.id.defect_type_list_view);
        defectTypeListView.setAdapter(defectTypeAdapter);


    }
}
