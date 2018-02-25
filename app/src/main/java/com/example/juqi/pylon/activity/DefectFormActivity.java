package com.example.juqi.pylon.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juqi.pylon.MyApplication;
import com.example.juqi.pylon.R;
import com.example.juqi.pylon.adapter.DefectSpinnerAdapter;
import com.example.juqi.pylon.database.TowerDatabaseHelper;
import com.example.juqi.pylon.entity.TowerInfoEntity;
import com.example.juqi.pylon.utils.ProgressCircle;
import org.json.JSONObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*缺陷记录的填写与保存与提交*/
public class DefectFormActivity extends TitleActivity {
    private String defectType;
    private String lineName;
    private Integer towerNum;
    private String voltageLevel;
    private String defectLevel;
    private String defectContent;
    private String discoverer;
    private String discoveryDate;
    private String isEliminated;
    private Boolean isEliminatedBoolean;
    private String isPMS ;
    private Boolean isPMSBoolean;
    private String eliminationDate;
    private String eliminationPerson;

    private ArrayList<String> defectContentSpinnerList;
    private TowerInfoEntity towerInfo;
    private TextView defectTypeTitle;
    private ImageView progressCircleView;
    private ProgressCircle progressCircle;
    private Spinner voltageLevelSpinner;
    private Spinner defectLevelSpinner;
    private Spinner defectContentSpinner;
    private TextView discoveryDateText;
    private EditText discovererText;
    private Spinner isEliminatedSpinner;
    private LinearLayout eliminationDateLinear;
    private LinearLayout eliminationPersonLinear;
    private TextView eliminationDateText;
    private EditText eliminationPersonText;
    private Spinner isPMSSpinner;
    private Button defectSubmit;
    private String todayFormat;

    private JSONObject recordJson = new JSONObject();
    private TowerDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_defect_form);

        //创建数据库
        dbHelper = new TowerDatabaseHelper(this, "TowerDefect.db", null, 1);
        dbHelper.getWritableDatabase();
        initDefectForm();
        Log.d("title","DefectForm onCreate");
        setTitle(defectType);
        Log.d("title","DefectForm SetTitle");
        setDefectForm();
        Log.d("title","DefectForm setDefectForm");
    }

    /*初始化缺陷记录各选项*/
    private void initDefectForm(){
        towerInfo = (TowerInfoEntity) getIntent().getSerializableExtra("tower_info");
        defectType = getIntent().getStringExtra("defect_type");
        defectContentSpinnerList = getIntent().getStringArrayListExtra("defect_content");
        //Log.d("form",defectContentSpinnerList.get(0));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date(System.currentTimeMillis());
        todayFormat = format.format(today);
        /*//设置标题
        defectTypeTitle = (TextView) findViewById(R.id.defect_type_title);
        defectTypeTitle.setText(defectType);*/

        progressCircleView = (ImageView) findViewById(R.id.defect_progress_circle);
        progressCircle = new ProgressCircle(DefectFormActivity.this, 0xff4354b5, progressCircleView);
        progressCircle.setCircle();

        voltageLevelSpinner = (Spinner) findViewById(R.id.voltage_level);
        defectLevelSpinner = (Spinner) findViewById(R.id.defect_level);
        discoveryDateText = (TextView) findViewById(R.id.descovery_date);
        discoveryDateText.setText(todayFormat);
        discovererText = (EditText) findViewById(R.id.discoverer);

        isEliminatedSpinner = (Spinner) findViewById(R.id.is_eliminated);
        eliminationDateLinear = (LinearLayout) findViewById(R.id.elimination_date_linear);
        eliminationPersonLinear = (LinearLayout) findViewById(R.id.elimination_person_linear);
        eliminationDateText = (TextView) findViewById(R.id.elimination_date);
        eliminationPersonText = (EditText) findViewById(R.id.elimination_person);
        isPMSSpinner = (Spinner) findViewById(R.id.is_PMS);

        defectContentSpinner = (Spinner) findViewById(R.id.defect_content);
        DefectSpinnerAdapter defectContentSpinnerAdapter = new DefectSpinnerAdapter(DefectFormActivity.this, defectContentSpinnerList);
        defectContentSpinner.setAdapter(defectContentSpinnerAdapter);


    }

    //进行缺陷记录填写
    private void setDefectForm() {
        //是否消缺
        isEliminatedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("是")) {
                    //Looper.prepare();
                    AlertDialog.Builder builder = new AlertDialog.Builder(DefectFormActivity.this);
                    builder.setMessage("确定已经消缺了吗");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eliminationDateText.setText(todayFormat);
                            eliminationPersonText.setText(discovererText.getText().toString());
                            eliminationDateLinear.setVisibility(View.VISIBLE);
                            eliminationPersonLinear.setVisibility(View.VISIBLE);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                    //Looper.loop();
                } else {
                    eliminationDateLinear.setVisibility(View.GONE);
                    eliminationPersonLinear.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //点击提交按钮
        defectSubmit = (Button) findViewById(R.id.defect_submit);
        defectSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defectSubmit.setEnabled(false);//按钮设置失效，防止重复操作
                if (discovererText.getText().toString().equals("")) {
                    Toast.makeText(DefectFormActivity.this, "请填写发现人员", Toast.LENGTH_SHORT).show();
                    defectSubmit.setEnabled(true);
                } else if (eliminationPersonText.getText().toString().equals("") && isEliminatedSpinner.getSelectedItem().toString().equals("是")) {
                    Toast.makeText(DefectFormActivity.this, "请填写消缺人员", Toast.LENGTH_SHORT).show();
                    defectSubmit.setEnabled(true);
                } else {
                    try {
                        progressCircle.startCircle();
                        saveDefect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
               // defectSubmit.setEnabled(true);
            }
        });
    }

    /*缺陷记录保存到本地*/
    private void saveDefect() throws IOException {

        lineName = towerInfo.getLineName();
        towerNum = towerInfo.getTowerNum();
        voltageLevel = voltageLevelSpinner.getSelectedItem().toString();
        defectLevel = defectLevelSpinner.getSelectedItem().toString();
        defectContent = defectContentSpinner.getSelectedItem().toString();
        discoverer = discovererText.getText().toString();
        discoveryDate = discoveryDateText.getText().toString();
        isEliminated = isEliminatedSpinner.getSelectedItem().toString();
        isEliminatedBoolean = (isEliminated.equals("是") ? true : false);
        isPMS = isPMSSpinner.getSelectedItem().toString();
        isPMSBoolean = (isPMS.equals("是") ? true : false);

        if (isEliminated.equals("是")) {
            eliminationDate = eliminationDateText.getText().toString();
            eliminationPerson = eliminationPersonText.getText().toString();
        } else {
            eliminationDate = "未消缺";
            eliminationPerson = "未消缺";
        }
        Log.d("record", recordJson.toString());

        //添加本地缺陷数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lineName", lineName);
        Log.d("database", "lineName");
        values.put("towerNum", String.valueOf(towerNum));//转成字符串
        values.put("defectType", defectType);
        values.put("voltageLevel", voltageLevel);
        values.put("defectLevel", defectLevel);
        values.put("content", defectContent);
        values.put("discoveryDate", discoveryDate);
        values.put("recordPerson", discoverer);
        values.put("isDel", isEliminated);
        values.put("delPerson", eliminationPerson);
        values.put("delTime", eliminationDate);
        values.put("isPMS", isPMS);
        db.insert("Tower", null, values);
        db.close();
        Log.d("database", "insert");
        Toast.makeText(DefectFormActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DefectFormActivity.this,MainActivity.class);
        intent.putExtra("tower_info",towerInfo);
        startActivity(intent);

        progressCircle.stopCircle();
    }

    @Override
    public void onDestroy() {
        MyApplication.getInstance().removeActivity(this);
        super.onDestroy();
    }

}

