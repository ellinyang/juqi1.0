package com.example.juqi.pylon.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.juqi.pylon.MyApplication;
import com.example.juqi.pylon.R;
import com.example.juqi.pylon.adapter.InquireDefectHistoryCommonAdapter;
import com.example.juqi.pylon.database.ExcelUtil;
import com.example.juqi.pylon.database.TowerDatabaseHelper;
import com.example.juqi.pylon.entity.DefectHistoryItem;
import com.example.juqi.pylon.utils.ProgressCircle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WriteException;

/**
 * Created by ylj_y on 2016/10/25.
 */
public class DefectHistoryInquireActivity extends Activity {
    private Context context;
    private ListView defectHistoryRecordListView;
    //private InquireDefectHistoryAdapter inquireDefectHistoryAdapter;
    private ImageView progressCircleView;
    private ProgressCircle progressCircle;
    private TowerDatabaseHelper dbHelper;
    private Cursor cursor;
    private int selectFlag;
    private final int ALLDEFECTS = 0;
    private final int UNELIMILATED = 1;
    private final int ELIMILATED = 2;
    private ProgressDialog progressDialog;
    private List<DefectHistoryItem> defectHistoryItemList;
    private Toolbar inquireTitleToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.defect_history_inquire_activity);
        defectHistoryRecordListView = (ListView)findViewById(R.id.defect_history_inquire_list_view);
        //工具栏
        inquireTitleToolbar = (Toolbar) findViewById(R.id.inquire_title_toolbar);
        inquireTitleToolbar.inflateMenu(R.menu.toolbar_filter);
        inquireTitleToolbar.setTitle(R.string.all_defect);

        inquireTitleToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        inquireTitleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        selectFlag=ALLDEFECTS;

        dbHelper = new TowerDatabaseHelper(this,"TowerDefect.db",null,1);//创建数据库
        Log.d("database","new dbHelper");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.query("Tower",null,null,null,null,null,null);
        context = this;
        progressCircleView = (ImageView)findViewById(R.id.inquire_progress_circle) ;
        progressCircle = new ProgressCircle(DefectHistoryInquireActivity.this,0xff4354b5,progressCircleView);
        progressCircle.setCircle();

        displayPic(cursor);
        /*HistoryTask historyTask = new HistoryTask();
        historyTask.execute();*/
        //db.close();
        //工具栏按钮设置监听
        inquireTitleToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    //导出所有记录
                    case R.id.action_export:
                        cursor = db.query("Tower",null,null,null,null,null,null);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(DefectHistoryInquireActivity.this);
                        dialog.setTitle("提醒");
                        dialog.setMessage("确认导出所有记录？");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){

                                progressDialog = new ProgressDialog(DefectHistoryInquireActivity.this);
                                progressDialog.setTitle("请稍等");
                                progressDialog.setMessage("正在导出...");
                                progressDialog.setCancelable(true);//不能通过back键取消ProgressDialog;
                                progressDialog.show();
                                //获取满足条件的所有记录
                                defectHistoryItemList = new ArrayList<DefectHistoryItem>();
                                if(cursor.moveToFirst()){
                                    int n = 0;
                                    do {
                                        DefectHistoryItem defectHistoryItem = new DefectHistoryItem();
                                        n++;
                                        defectHistoryItem.setId(n);
                                        Log.d("excel",cursor.getString(cursor.getColumnIndex("lineName")));
                                        defectHistoryItem.setLineName(cursor.getString(cursor.getColumnIndex("lineName")));
                                        defectHistoryItem.setTowerNum(Integer.valueOf(cursor.getString(cursor.getColumnIndex("towerNum"))));
                                        defectHistoryItem.setDefectType(cursor.getString(cursor.getColumnIndex("defectType")));
                                        defectHistoryItem.setDefectLevel(cursor.getString(cursor.getColumnIndex("defectLevel")));
                                        defectHistoryItem.setContent(cursor.getString(cursor.getColumnIndex("content")));
                                        defectHistoryItem.setRecordTime(cursor.getString(cursor.getColumnIndex("discoveryDate")));
                                        defectHistoryItem.setRecordPerson(cursor.getString(cursor.getColumnIndex("recordPerson")));
                                        defectHistoryItem.setIsDel(cursor.getString(cursor.getColumnIndex("isDel")).equals("是")? true : false);
                                        defectHistoryItem.setDelPerson(cursor.getString(cursor.getColumnIndex("delPerson")));
                                        defectHistoryItem.setIsPMS(cursor.getString(cursor.getColumnIndex("isPMS")).equals("是")? true : false);
                                        defectHistoryItem.setVoltageLevel(cursor.getString(cursor.getColumnIndex("voltageLevel")));
                                        defectHistoryItem.setDelTime(cursor.getString(cursor.getColumnIndex("delTime")));
                                        defectHistoryItemList.add(defectHistoryItem);

                                    }while (cursor.moveToNext());
                                }
                                handler.sendEmptyMessage(0);// 执行耗时的方法即缓存完之后发送消给handler
                            }
                        });

                        dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){

                            }
                        });
                        dialog.show();
                        break;

                    //设置标题，并显示所有缺陷记录
                    case R.id.filter_all_defect:
                        inquireTitleToolbar.setTitle(R.string.all_defect);
                        selectFlag = ALLDEFECTS;
                        cursor = db.query("Tower",null,null,null,null,null,null);
                        /*HistoryTask historyTask1 = new HistoryTask();
                        historyTask1.execute();*/
                        displayPic(cursor);
                        //Toast.makeText(DefectHistoryInquireActivity.this,"所有缺陷",Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {// handler接收到消息后就会执行此方法

            try {
                //将所查到的数据导出到excel
                if(defectHistoryItemList!=null){
                    ExcelUtil excelUtil = new ExcelUtil(DefectHistoryInquireActivity.this,defectHistoryItemList);
                    excelUtil.ExportToExcel((String) inquireTitleToolbar.getTitle());
                }else{
                    Toast.makeText(context,"本地数据库中没有历史缺陷", Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //删除已导出的记录
//             if(selectFlag==ELIMILATED){
//                 db.delete("Tower","isDel=?",new String[]{"是"});
//             }else if (selectFlag==UNELIMILATED){
//                 db.delete("Tower","isDel=?",new String[]{"否"});
//             }else db.delete("Tower",null,null);

            displayPic(cursor);
            cursor.close();
            progressDialog.dismiss();// 关闭ProgressDialog
            Intent intent = new Intent(DefectHistoryInquireActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };


    //获取数据库指针指向的集合数据，并创建适配器，初始化界面
    private void displayPic(Cursor cursor){
        if(cursor.getCount() != 0){
            Log.d("database","AsyncTask cursor.getCount != 0");
            List<DefectHistoryItem> data = new ArrayList<DefectHistoryItem>();
            if(cursor.moveToFirst()){
                int n = 0;
                do {
                    DefectHistoryItem defectHistoryItem = new DefectHistoryItem();
                    n++;
                    defectHistoryItem.setId(n);
                    Log.d("excel",cursor.getString(cursor.getColumnIndex("lineName")));
                    defectHistoryItem.setLineName(cursor.getString(cursor.getColumnIndex("lineName")));
                    defectHistoryItem.setTowerNum(Integer.valueOf(cursor.getString(cursor.getColumnIndex("towerNum"))));
                    defectHistoryItem.setDefectType(cursor.getString(cursor.getColumnIndex("defectType")));
                    defectHistoryItem.setDefectLevel(cursor.getString(cursor.getColumnIndex("defectLevel")));
                    defectHistoryItem.setContent(cursor.getString(cursor.getColumnIndex("content")));
                    defectHistoryItem.setRecordTime(cursor.getString(cursor.getColumnIndex("discoveryDate")));
                    defectHistoryItem.setRecordPerson(cursor.getString(cursor.getColumnIndex("recordPerson")));
                    defectHistoryItem.setIsDel(cursor.getString(cursor.getColumnIndex("isDel")).equals("是") ? true : false);
                    defectHistoryItem.setDelPerson(cursor.getString(cursor.getColumnIndex("delPerson")));
                    defectHistoryItem.setIsPMS(cursor.getString(cursor.getColumnIndex("isPMS")).equals("是") ? true : false);
                    defectHistoryItem.setVoltageLevel(cursor.getString(cursor.getColumnIndex("voltageLevel")));
                    defectHistoryItem.setDelTime(cursor.getString(cursor.getColumnIndex("delTime")));
                    data.add(defectHistoryItem);

                }while (cursor.moveToNext());
            }
            Log.d("excel", String.valueOf(data.size()));
            InquireDefectHistoryCommonAdapter inquireDefectHistoryCommonAdapter = new InquireDefectHistoryCommonAdapter(DefectHistoryInquireActivity.this,data);
            defectHistoryRecordListView.setAdapter(inquireDefectHistoryCommonAdapter);

        }else{
            progressCircle.stopCircle();
            Toast.makeText(context,"本地数据库中没有历史缺陷", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,MainActivity.class);
            startActivity(intent);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            cursor.close();
            db.close();
            finish();
        }

    }

    @Override
    public void onDestroy(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor.close();
        db.close();
        MyApplication.getInstance().removeActivity(this);
        super.onDestroy();
    }



}
