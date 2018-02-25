package com.example.juqi.pylon.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.example.juqi.pylon.R;

import com.example.juqi.pylon.database.ExcelUtil;
import com.example.juqi.pylon.database.TowerDatabaseHelper;
import com.example.juqi.pylon.entity.DefectHistoryItem;
import com.example.juqi.pylon.utils.NumberFormatUtil;
import com.example.juqi.pylon.utils.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WriteException;


/**
 * Created by ylj_y on 2016/11/28.
 */
public class InquireDefectHistoryCommonAdapter extends CommonAdapter<DefectHistoryItem> {
    private List<DefectHistoryItem> mList;
    private DefectHistoryItem defectHistoryItem;
    private String eliminationDate;
    private String eliminationPerson;
    private String lineName;
    private int towerNum;
    private String defectType;
    private String content;
    private String recordData;
    private TowerDatabaseHelper dbHelper;

    private Context mContext;
   /* List<DefectHistoryItem> mData;
    LayoutInflater mInflater;
    int mLayoutId;*/


     public InquireDefectHistoryCommonAdapter(Context context, List<DefectHistoryItem> data) {
        super(context, data, R.layout.defect_history_inquire_record);
        mContext = context;
        mList=data;

    }

   @Override
    public void convert(final ViewHolder holder, final DefectHistoryItem defectHistoryItem) {

        this.defectHistoryItem = defectHistoryItem;
        lineName = defectHistoryItem.getLineName();
        towerNum = defectHistoryItem.getTowerNum();
        defectType = defectHistoryItem.getDefectType();
        content = defectHistoryItem.getContent();
        recordData = defectHistoryItem.getRecordTime();

        holder.setText(R.id.inquire_defect_id, "缺陷记录" + NumberFormatUtil.formatInteger(defectHistoryItem.getId()))
                .setText(R.id.inquire_defect_tower_name,lineName+towerNum+"塔")
                .setText(R.id.inquire_defect_type, defectType)
                .setText(R.id.inquire_voltage_level, defectHistoryItem.getVoltageLevel())
                .setText(R.id.inquire_defect_level, defectHistoryItem.getDefectLevel())
                .setText(R.id.inquire_defect_content, content)
                .setText(R.id.inquire_discovery_date, recordData)
                .setText(R.id.inquire_discoverer, defectHistoryItem.getRecordPerson())
                .setText(R.id.inquire_is_eliminated, (defectHistoryItem.getIsDel() == true ? "是" : "否"))
                .setText(R.id.inquire_elimination_date, defectHistoryItem.getDelTime())
                .setText(R.id.inquire_elimination_person, defectHistoryItem.getDelPerson())
                .setText(R.id.inquire_is_PMS, (defectHistoryItem.getIsPMS() == true ? "是" : "否"));


        if(defectHistoryItem.getIsDel()){
            holder.getView(R.id.inquire_discovery_date_linear).setVisibility(View.VISIBLE);
            holder.getView(R.id.inquire_elimination_person_linear).setVisibility(View.VISIBLE);
            holder.getView(R.id.inquire_gone_divider_1).setVisibility(View.VISIBLE);
            holder.getView(R.id.inquire_gone_divider_2).setVisibility(View.VISIBLE);
        }else {
            holder.getView(R.id.inquire_discovery_date_linear).setVisibility(View.GONE);
            holder.getView(R.id.inquire_elimination_person_linear).setVisibility(View.GONE);
            holder.getView(R.id.inquire_gone_divider_1).setVisibility(View.GONE);
            holder.getView(R.id.inquire_gone_divider_2).setVisibility(View.GONE);
        }

        //判断是否消缺，并为按键设置监听事件,获取消缺人名和消缺时间
    }

}

