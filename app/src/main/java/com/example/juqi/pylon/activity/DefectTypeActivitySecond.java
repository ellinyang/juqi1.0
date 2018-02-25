package com.example.juqi.pylon.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juqi.pylon.adapter.DefectTypeAdapter;
import com.example.juqi.pylon.entity.DefectTypeItem;
import com.example.juqi.pylon.MyApplication;
import com.example.juqi.pylon.R;
import com.example.juqi.pylon.entity.TowerInfoEntity;

import java.util.ArrayList;
import java.util.List;

public class DefectTypeActivitySecond extends TitleActivity {

    private TowerInfoEntity towerInfo;
    private String defectTypeUnitName;
    private TextView textView;
    private List<DefectTypeItem> defectTypeList = new ArrayList<>();
    ListView defectTypeListView;
    DefectTypeAdapter defectTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_defect_type);
        towerInfo = (TowerInfoEntity)getIntent().getSerializableExtra("tower_info");
        defectTypeUnitName = getIntent().getStringExtra("defect_type");
        //textView =(TextView)findViewById(R.id.activity_defect_type_title);
        //textView.setText(defectTypeUnitName);
        Log.d("title","Second onCreate");
        setTitle(defectTypeUnitName);
        Log.d("title","Second setTitle");
        setDefectType();
        Log.d("title","Second setDefectType");
    }

    private void setDefectType() {
        switch (defectTypeUnitName){
            case "电缆本体":
                DefectTypeItem defectTypeItem00 = new DefectTypeItem();
                defectTypeItem00.setFirstType("电缆芯线");
                ArrayList<String> firstSpinnerList00 = new ArrayList<>();
                firstSpinnerList00.add("导体直径偏小");
                firstSpinnerList00.add("导体有毛刺");
                firstSpinnerList00.add("直流电阻偏大");
                firstSpinnerList00.add("三相不平衡系数大于2");
                firstSpinnerList00.add("对地电容偏大");
                firstSpinnerList00.add("浸水");
                firstSpinnerList00.add("受潮");
                defectTypeItem00.setFirstSpinnerList(firstSpinnerList00);

                defectTypeItem00.setSecondType("主绝缘");
                ArrayList<String> secondSpinnerList00 = new ArrayList<>();
                secondSpinnerList00.add("电阻不合格");
                secondSpinnerList00.add("局放偏大");
                secondSpinnerList00.add("耐压试验不合格");
                secondSpinnerList00.add("本体击穿");
                defectTypeItem00.setSecondSpinnerList(secondSpinnerList00);
                defectTypeList.add(defectTypeItem00);

                DefectTypeItem defectTypeItem01 = new DefectTypeItem();
                defectTypeItem01.setFirstType("金属护套");
                ArrayList<String> firstSpinnerList01 = new ArrayList<>();
                firstSpinnerList01.add("厚度偏小");
                firstSpinnerList01.add("有裂缝");
                defectTypeItem01.setFirstSpinnerList(firstSpinnerList01);

                defectTypeItem01.setSecondType("外护套");
                ArrayList<String> secondSpinnerList01 = new ArrayList<>();
                secondSpinnerList01.add("厚度偏小");
                secondSpinnerList01.add("绝缘电阻偏小");
                secondSpinnerList01.add("破损");
                secondSpinnerList01.add("龟裂");
                secondSpinnerList01.add("耐压试验不合格");
                secondSpinnerList01.add("护套击穿");
                defectTypeItem01.setSecondSpinnerList(secondSpinnerList01);
                defectTypeList.add(defectTypeItem01);

                DefectTypeItem defectTypeItem02 = new DefectTypeItem();
                defectTypeItem02.setFirstType("铜屏蔽");
                ArrayList<String> firstSpinnerList02 = new ArrayList<>();
                firstSpinnerList02.add("搭盖率偏小");
                firstSpinnerList02.add("截面积偏小");
                firstSpinnerList02.add("锈蚀");
                firstSpinnerList02.add("断股");
                defectTypeItem02.setFirstSpinnerList(firstSpinnerList02);

                defectTypeItem02.setSecondType("绝缘屏蔽");
                ArrayList<String> secondSpinnerList02 = new ArrayList<>();
                secondSpinnerList02.add("有毛刺");
                secondSpinnerList02.add("不平整");
                defectTypeItem02.setSecondSpinnerList(secondSpinnerList02);
                defectTypeList.add(defectTypeItem02);
                break;
            case "电缆终端":
                DefectTypeItem defectTypeItem10 = new DefectTypeItem();
                defectTypeItem10.setFirstType("设备线夹");
                ArrayList<String> firstSpinnerList10 = new ArrayList<>();
                firstSpinnerList10.add("发热");
                firstSpinnerList10.add("螺栓松动");
                firstSpinnerList10.add("锈蚀");
                firstSpinnerList10.add("开裂");
                defectTypeItem10.setFirstSpinnerList(firstSpinnerList10);

                defectTypeItem10.setSecondType("导体连接棒");
                ArrayList<String> secondSpinnerList10 = new ArrayList<>();
                secondSpinnerList10.add("锈蚀");
                secondSpinnerList10.add("发热");
                secondSpinnerList10.add("接触电阻偏大");
                defectTypeItem10.setSecondSpinnerList(secondSpinnerList10);
                defectTypeList.add(defectTypeItem10);

                DefectTypeItem defectTypeItem11 = new DefectTypeItem();
                defectTypeItem11.setFirstType("防雨罩");
                ArrayList<String> firstSpinnerList11 = new ArrayList<>();
                firstSpinnerList11.add("老化");
                firstSpinnerList11.add("破损");
                defectTypeItem11.setFirstSpinnerList(firstSpinnerList11);

                defectTypeItem11.setSecondType("终端套管");
                ArrayList<String> secondSpinnerList11 = new ArrayList<>();
                secondSpinnerList11.add("裙边有破损");
                secondSpinnerList11.add("裙边有裂纹");
                secondSpinnerList11.add("放电");
                secondSpinnerList11.add("闪络");
                secondSpinnerList11.add("积污");
                defectTypeItem11.setSecondSpinnerList(secondSpinnerList11);
                defectTypeList.add(defectTypeItem11);

                DefectTypeItem defectTypeItem12 = new DefectTypeItem();
                defectTypeItem12.setFirstType("密封装置");
                ArrayList<String> firstSpinnerList12 = new ArrayList<>();
                firstSpinnerList12.add("密封圈老化");
                firstSpinnerList12.add("渗漏油");
                defectTypeItem12.setFirstSpinnerList(firstSpinnerList12);

                defectTypeItem12.setSecondType("法兰盘");
                ArrayList<String> secondSpinnerList12 = new ArrayList<>();
                secondSpinnerList12.add("松动");
                secondSpinnerList12.add("缺螺帽");
                defectTypeItem12.setSecondSpinnerList(secondSpinnerList12);
                defectTypeList.add(defectTypeItem12);

                DefectTypeItem defectTypeItem13 = new DefectTypeItem();
                defectTypeItem13.setFirstType("支持绝缘子");
                ArrayList<String> firstSpinnerList13 = new ArrayList<>();
                firstSpinnerList13.add("破损");
                firstSpinnerList13.add("松动");
                defectTypeItem13.setFirstSpinnerList(firstSpinnerList13);
                defectTypeList.add(defectTypeItem13);

                break;
            case "辅助设备":
                DefectTypeItem defectTypeItem20 = new DefectTypeItem();
                defectTypeItem20.setFirstType("电缆支架");
                ArrayList<String> firstSpinnerList20 = new ArrayList<>();
                firstSpinnerList20.add("锈蚀");
                firstSpinnerList20.add("缺件");
                firstSpinnerList20.add("损坏");
                firstSpinnerList20.add("接地不良");
                firstSpinnerList20.add("变形");
                defectTypeItem20.setFirstSpinnerList(firstSpinnerList20);

                defectTypeItem20.setSecondType("终端底座");
                ArrayList<String> secondSpinnerList20 = new ArrayList<>();
                secondSpinnerList20.add("倾斜");
                secondSpinnerList20.add("锈蚀");
                secondSpinnerList20.add("松动");
                secondSpinnerList20.add("未隔磁");
                defectTypeItem20.setSecondSpinnerList(secondSpinnerList20);
                defectTypeList.add(defectTypeItem20);

                DefectTypeItem defectTypeItem21 = new DefectTypeItem();
                defectTypeItem21.setFirstType("抱箍");
                ArrayList<String> firstSpinnerList21 = new ArrayList<>();
                firstSpinnerList21.add("螺栓脱落");
                firstSpinnerList21.add("损坏");
                firstSpinnerList21.add("缺失");
                firstSpinnerList21.add("锈蚀");
                firstSpinnerList21.add("未隔磁");
                defectTypeItem21.setFirstSpinnerList(firstSpinnerList21);

                defectTypeItem21.setSecondType("接地箱");
                ArrayList<String> secondSpinnerList21 = new ArrayList<>();
                secondSpinnerList21.add("基础损坏");
                secondSpinnerList21.add("保护罩损坏");
                secondSpinnerList21.add("箱体损坏");
                secondSpinnerList21.add("箱体缺失");
                secondSpinnerList21.add("交叉互联换位错误");
                secondSpinnerList21.add("护层保护器损坏");
                secondSpinnerList21.add("母排与接地箱外壳不绝缘");
                secondSpinnerList21.add("环流偏大");
                defectTypeItem21.setSecondSpinnerList(secondSpinnerList21);
                defectTypeList.add(defectTypeItem21);

                DefectTypeItem defectTypeItem22 = new DefectTypeItem();
                defectTypeItem22.setFirstType("接地单芯引缆");
                ArrayList<String> firstSpinnerList22 = new ArrayList<>();
                firstSpinnerList22.add("连接松动");
                firstSpinnerList22.add("损坏");
                firstSpinnerList22.add("缺失");
                defectTypeItem22.setFirstSpinnerList(firstSpinnerList22);

                defectTypeItem22.setSecondType("同轴电缆");
                ArrayList<String> secondSpinnerList22 = new ArrayList<>();
                secondSpinnerList22.add("与电缆金属护套连接错误");
                secondSpinnerList22.add("同轴电缆损坏");
                secondSpinnerList22.add("击穿");
                defectTypeItem22.setSecondSpinnerList(secondSpinnerList22);
                defectTypeList.add(defectTypeItem22);

                DefectTypeItem defectTypeItem23 = new DefectTypeItem();
                defectTypeItem23.setFirstType("主接地引缆");
                ArrayList<String> firstSpinnerList23 = new ArrayList<>();
                firstSpinnerList23.add("与接地箱接地母排连接松动");
                firstSpinnerList23.add("损坏");
                firstSpinnerList23.add("与接地网连接松动");
                firstSpinnerList23.add("与接地网连接断开");
                defectTypeItem23.setFirstSpinnerList(firstSpinnerList23);

                defectTypeItem23.setSecondType("接地网");
                ArrayList<String> secondSpinnerList23 = new ArrayList<>();
                secondSpinnerList23.add("接地电阻偏大");
                secondSpinnerList23.add("焊接部位未做防腐处理");
                secondSpinnerList23.add("锈蚀");
                secondSpinnerList23.add("接地线断开");
                defectTypeItem23.setSecondSpinnerList(secondSpinnerList23);
                defectTypeList.add(defectTypeItem23);

                DefectTypeItem defectTypeItem24 = new DefectTypeItem();
                defectTypeItem24.setFirstType("回流线(缆)");
                ArrayList<String> firstSpinnerList24 = new ArrayList<>();
                firstSpinnerList24.add("连接松动");
                firstSpinnerList24.add("连接断开");
                firstSpinnerList24.add("损坏");
                firstSpinnerList24.add("缺失");
                defectTypeItem24.setFirstSpinnerList(firstSpinnerList24);

                defectTypeItem24.setSecondType("供油装置");
                ArrayList<String> secondSpinnerList24 = new ArrayList<>();
                secondSpinnerList24.add("漏油");
                secondSpinnerList24.add("损坏");
                secondSpinnerList24.add("油压计损坏");
                defectTypeItem24.setSecondSpinnerList(secondSpinnerList24);
                defectTypeList.add(defectTypeItem24);

                DefectTypeItem defectTypeItem25 = new DefectTypeItem();
                defectTypeItem25.setFirstType("测温光缆");
                ArrayList<String> firstSpinnerList25 = new ArrayList<>();
                firstSpinnerList25.add("损坏");
                firstSpinnerList25.add("缺失");

                defectTypeItem25.setFirstSpinnerList(firstSpinnerList25);

                defectTypeItem25.setSecondType("测温系统主机");
                ArrayList<String> secondSpinnerList25 = new ArrayList<>();
                secondSpinnerList25.add("软件故障");
                secondSpinnerList25.add("硬件故障");
                defectTypeItem25.setSecondSpinnerList(secondSpinnerList25);
                defectTypeList.add(defectTypeItem25);

                DefectTypeItem defectTypeItem26 = new DefectTypeItem();
                defectTypeItem26.setFirstType("防火槽盒(等)");
                ArrayList<String> firstSpinnerList26 = new ArrayList<>();
                firstSpinnerList26.add("防火板损坏");
                firstSpinnerList26.add("防火板缺失");
                defectTypeItem26.setFirstSpinnerList(firstSpinnerList26);

                defectTypeItem26.setSecondType("防火带(防火涂料)");
                ArrayList<String> secondSpinnerList26 = new ArrayList<>();
                secondSpinnerList26.add("脱落");
                secondSpinnerList26.add("缺失");
                defectTypeItem26.setSecondSpinnerList(secondSpinnerList26);
                defectTypeList.add(defectTypeItem26);


                DefectTypeItem defectTypeItem27 = new DefectTypeItem();
                defectTypeItem27.setFirstType("电缆线路铭牌");
                ArrayList<String> firstSpinnerList27 = new ArrayList<>();
                firstSpinnerList27.add("字迹不清");
                firstSpinnerList27.add("铭牌缺失");
                firstSpinnerList27.add("铭牌错误");
                defectTypeItem27.setFirstSpinnerList(firstSpinnerList27);

                defectTypeItem27.setSecondType("相位牌");
                ArrayList<String> secondSpinnerList27= new ArrayList<>();
                secondSpinnerList27.add("字迹不清");
                secondSpinnerList27.add("铭牌缺失");
                defectTypeItem27.setSecondSpinnerList(secondSpinnerList27);
                defectTypeList.add(defectTypeItem27);

                DefectTypeItem defectTypeItem28 = new DefectTypeItem();
                defectTypeItem28.setFirstType("路径指(警)示牌");
                ArrayList<String> firstSpinnerList28 = new ArrayList<>();
                firstSpinnerList28.add("损坏");
                firstSpinnerList28.add("缺失");
                firstSpinnerList28.add("字迹不清");
                defectTypeItem28.setFirstSpinnerList(firstSpinnerList28);

                defectTypeItem28.setSecondType("接地箱铭牌");
                ArrayList<String> secondSpinnerList28 = new ArrayList<>();
                secondSpinnerList28.add("字迹不清");
                secondSpinnerList28.add("铭牌缺失");
                defectTypeItem28.setSecondSpinnerList(secondSpinnerList28);
                defectTypeList.add(defectTypeItem28);

                DefectTypeItem defectTypeItem29 = new DefectTypeItem();
                defectTypeItem29.setFirstType("安全警告牌");
                ArrayList<String> firstSpinnerList29 = new ArrayList<>();
                firstSpinnerList29.add("字迹不清");
                firstSpinnerList29.add("缺失");
                defectTypeItem29.setFirstSpinnerList(firstSpinnerList29);
                defectTypeList.add(defectTypeItem29);
                break;
            case "中间接头":
                DefectTypeItem defectTypeItem30 = new DefectTypeItem();
                defectTypeItem30.setFirstType("接地铜编织带");
                ArrayList<String> firstSpinnerList30 = new ArrayList<>();
                firstSpinnerList30.add("虚焊");
                firstSpinnerList30.add("绝缘不合格");
                firstSpinnerList30.add("连接不良");

                defectTypeItem30.setFirstSpinnerList(firstSpinnerList30);

                defectTypeItem30.setSecondType("铜外壳");
                ArrayList<String> secondSpinnerList30 = new ArrayList<>();
                secondSpinnerList30.add("受撞击(挤压)变形");
                secondSpinnerList30.add("密封不良");
                secondSpinnerList30.add("长、短头绝缘不合格");
                defectTypeItem30.setSecondSpinnerList(secondSpinnerList30);
                defectTypeList.add(defectTypeItem30);

                DefectTypeItem defectTypeItem31 = new DefectTypeItem();
                defectTypeItem31.setFirstType("环氧外壳");
                ArrayList<String> firstSpinnerList31 = new ArrayList<>();
                firstSpinnerList31.add("破损");
                firstSpinnerList31.add("老化");
                firstSpinnerList31.add("密封不良");
                defectTypeItem31.setFirstSpinnerList(firstSpinnerList31);

                defectTypeItem31.setSecondType("密封胶");
                ArrayList<String> secondSpinnerList31 = new ArrayList<>();
                secondSpinnerList31.add("未能凝固");
                secondSpinnerList31.add("未填满");
                secondSpinnerList31.add("浸水");
                defectTypeItem31.setSecondSpinnerList(secondSpinnerList31);
                defectTypeList.add(defectTypeItem31);

                DefectTypeItem defectTypeItem32 = new DefectTypeItem();
                defectTypeItem32.setFirstType("接头底座(支架)");
                ArrayList<String> firstSpinnerList32 = new ArrayList<>();
                firstSpinnerList32.add("锈蚀");
                firstSpinnerList32.add("中间接头支架损坏");
                defectTypeItem32.setFirstSpinnerList(firstSpinnerList32);

                defectTypeItem32.setSecondType("主体");
                ArrayList<String> secondSpinnerList32 = new ArrayList<>();
                secondSpinnerList32.add("浸水");
                secondSpinnerList32.add("击穿");
                defectTypeItem32.setSecondSpinnerList(secondSpinnerList32);
                defectTypeList.add(defectTypeItem32);

                break;
            case "电缆线路避雷器":
                DefectTypeItem defectTypeItem40 = new DefectTypeItem();
                defectTypeItem40.setFirstType("本体");
                ArrayList<String> firstSpinnerList40 = new ArrayList<>();
                firstSpinnerList40.add("松动");
                firstSpinnerList40.add("脱落");
                firstSpinnerList40.add("破损");
                firstSpinnerList40.add("脱离器断开");
                firstSpinnerList40.add("缺件");
                firstSpinnerList40.add("引线断股");
                firstSpinnerList40.add("缺螺栓");
                firstSpinnerList40.add("试验不合格");
                firstSpinnerList40.add("泄漏值偏高");

                defectTypeItem40.setFirstSpinnerList(firstSpinnerList40);

                defectTypeItem40.setSecondType("监测仪(计数器)");
                ArrayList<String> secondSpinnerList40 = new ArrayList<>();
                secondSpinnerList40.add("进水");
                secondSpinnerList40.add("图文不清");
                secondSpinnerList40.add("表面破损");
                secondSpinnerList40.add("连线松动");
                secondSpinnerList40.add("连线避雷器端脱落");
                secondSpinnerList40.add("连线计数器端脱落");
                secondSpinnerList40.add("误指示");
                defectTypeItem40.setSecondSpinnerList(secondSpinnerList40);
                defectTypeList.add(defectTypeItem40);

                DefectTypeItem defectTypeItem41 = new DefectTypeItem();
                defectTypeItem41.setFirstType("支架");
                ArrayList<String> firstSpinnerList41 = new ArrayList<>();
                firstSpinnerList41.add("松动");
                firstSpinnerList41.add("脱落");
                defectTypeItem41.setFirstSpinnerList(firstSpinnerList41);

                defectTypeItem41.setSecondType("均压环");
                ArrayList<String> secondSpinnerList41 = new ArrayList<>();
                secondSpinnerList41.add("脱落");
                secondSpinnerList41.add("移位");
                secondSpinnerList41.add("缺失");
                defectTypeItem41.setSecondSpinnerList(secondSpinnerList41);
                defectTypeList.add(defectTypeItem41);

                break;
            case "通道":

                DefectTypeItem defectTypeItem50 = new DefectTypeItem();
                defectTypeItem50.setFirstType("接头工井");
                ArrayList<String> firstSpinnerList50 = new ArrayList<>();
                firstSpinnerList50.add("积水");
                firstSpinnerList50.add("基础下沉");
                firstSpinnerList50.add("墙体坍塌(破损)");
                firstSpinnerList50.add("盖板缺失");
                firstSpinnerList50.add("盖板破损");
                firstSpinnerList50.add("盖板不平整");
                defectTypeItem50.setFirstSpinnerList(firstSpinnerList50);

                defectTypeItem50.setSecondType("操作工井");
                ArrayList<String> secondSpinnerList50 = new ArrayList<>();
                secondSpinnerList50.add("基础下沉");
                secondSpinnerList50.add("墙壁塌翻(破损)");
                secondSpinnerList50.add("盖板缺失");
                secondSpinnerList50.add("盖板破损");
                secondSpinnerList50.add("盖板不平整");
                defectTypeItem50.setSecondSpinnerList(secondSpinnerList50);
                defectTypeList.add(defectTypeItem50);

                DefectTypeItem defectTypeItem51 = new DefectTypeItem();
                defectTypeItem51.setFirstType("电缆沟体");
                ArrayList<String> firstSpinnerList51 = new ArrayList<>();
                firstSpinnerList51.add("基础下沉");
                firstSpinnerList51.add("墙体坍塌(破损)");
                firstSpinnerList51.add("盖板缺失");
                firstSpinnerList51.add("盖板破损");
                firstSpinnerList51.add("盖板不平整");
                defectTypeItem51.setFirstSpinnerList(firstSpinnerList51);

                defectTypeItem51.setSecondType("绝缘子");
                ArrayList<String> secondSpinnerList51 = new ArrayList<>();
                secondSpinnerList51.add("复合绝缘子老化");
                secondSpinnerList51.add("绝缘子表面脏污");
                secondSpinnerList51.add("玻璃绝缘子自爆");
                defectTypeItem51.setSecondSpinnerList(secondSpinnerList51);
                defectTypeList.add(defectTypeItem51);

                DefectTypeItem defectTypeItem52 = new DefectTypeItem();
                defectTypeItem52.setFirstType("电缆排管");
                ArrayList<String> firstSpinnerList52 = new ArrayList<>();
                firstSpinnerList52.add("堵塞");
                firstSpinnerList52.add("管道内壁粗糙，有毛刺");
                firstSpinnerList52.add("变形");
                firstSpinnerList52.add("包方破损");
                firstSpinnerList52.add("破损");
                firstSpinnerList52.add("异物");
                firstSpinnerList52.add("接口错位");
                firstSpinnerList52.add("端口未封堵");
                defectTypeItem52.setFirstSpinnerList(firstSpinnerList52);

                defectTypeItem52.setSecondType("电缆隧道");
                ArrayList<String> secondSpinnerList52 = new ArrayList<>();
                secondSpinnerList52.add("渗水");
                secondSpinnerList52.add("排水设施损坏");
                secondSpinnerList52.add("有裂缝");
                secondSpinnerList52.add("照明设备损坏");
                secondSpinnerList52.add("通风设施损坏");
                secondSpinnerList52.add("门损坏");
                secondSpinnerList52.add("消防设施缺失");
                defectTypeItem52.setSecondSpinnerList(secondSpinnerList52);
                defectTypeList.add(defectTypeItem52);

                DefectTypeItem defectTypeItem53 = new DefectTypeItem();
                defectTypeItem53.setFirstType("隧道竖井");
                ArrayList<String> firstSpinnerList53 = new ArrayList<>();
                firstSpinnerList53.add("盖板损坏");
                firstSpinnerList53.add("盖板缺失");
                firstSpinnerList53.add("爬梯损坏");
                firstSpinnerList53.add("爬梯锈蚀");
                firstSpinnerList53.add("渗水");
                defectTypeItem53.setFirstSpinnerList(firstSpinnerList53);

                defectTypeItem53.setSecondType("电缆桥");
                ArrayList<String> secondSpinnerList53 = new ArrayList<>();
                secondSpinnerList53.add("桥墩基础下沉");
                secondSpinnerList53.add("基础覆土流失");
                secondSpinnerList53.add("桥体腐蚀");
                secondSpinnerList53.add("桥体围栏损坏");
                secondSpinnerList53.add("电缆遮阳(雨)棚损坏");
                secondSpinnerList53.add("接地电阻不合格");
                secondSpinnerList53.add("桥体倾斜");
                defectTypeItem53.setSecondSpinnerList(secondSpinnerList53);
                defectTypeList.add(defectTypeItem53);

                DefectTypeItem defectTypeItem54 = new DefectTypeItem();
                defectTypeItem54.setFirstType("其它管线");
                ArrayList<String> firstSpinnerList54 = new ArrayList<>();
                firstSpinnerList54.add("与煤气（天然气）管道距离不足");
                firstSpinnerList54.add("与自来水（污水）管道距离不足");
                firstSpinnerList54.add("与热力管道距离不足");
                firstSpinnerList54.add("与输油管道距离不足");
                firstSpinnerList54.add("在电缆线路保护区内有施工开挖");
                firstSpinnerList54.add("线路保护区内土壤流失严重");
                firstSpinnerList54.add("在电缆线路保护区内兴建构筑物");
                defectTypeItem54.setFirstSpinnerList(firstSpinnerList54);

                defectTypeItem54.setSecondType("电缆线路保护区");
                ArrayList<String> secondSpinnerList54 = new ArrayList<>();
                secondSpinnerList54.add("在电缆线路保护区内有施工开挖");
                secondSpinnerList54.add("线路保护区内土壤流失严重");
                secondSpinnerList54.add("在电缆线路保护区内兴建构筑物");

                defectTypeItem54.setSecondSpinnerList(secondSpinnerList54);
                defectTypeList.add(defectTypeItem54);

                break;
        }

        defectTypeAdapter = new DefectTypeAdapter(DefectTypeActivitySecond.this,defectTypeList,towerInfo);
        defectTypeListView = (ListView)findViewById(R.id.defect_type_list_view);
        defectTypeListView.setAdapter(defectTypeAdapter);


    }
}
