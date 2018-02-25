package com.example.juqi.pylon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.juqi.pylon.R;
import com.example.juqi.pylon.activity.DefectFormActivity;
import com.example.juqi.pylon.activity.DefectTypeActivitySecond;
import com.example.juqi.pylon.entity.DefectTypeItem;
import com.example.juqi.pylon.entity.TowerInfoEntity;
import com.example.juqi.pylon.utils.AnimationUtils;
import com.example.juqi.pylon.utils.ViewHolder;
import java.util.List;

public class DefectTypeAdapter extends CommonAdapter<DefectTypeItem> {

    Context context;
    TowerInfoEntity towerInfo;

    public DefectTypeAdapter(Context context, List<DefectTypeItem> data, TowerInfoEntity towerInfo){
        super(context,data,R.layout.defect_type_item);
        this.context = context;
        this.towerInfo = towerInfo;
    }

    @Override
    public void convert(final ViewHolder holder,final DefectTypeItem defectTypeItem){
        holder.setText(R.id.first_defect_item,defectTypeItem.getFirstType()).
                setText(R.id.second_defect_item,defectTypeItem.getSecondType());

        final TextView firstDefectItem = holder.getView(R.id.first_defect_item);
        final TextView secondDefectItem = holder.getView(R.id.second_defect_item);

        firstDefectItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        AnimationUtils.touchAnimate(firstDefectItem,firstDefectItem.getDrawingCacheBackgroundColor(),0xffcccccc,100);
                        break;
                    default:
                }
                return false;
            }
        });

        secondDefectItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        AnimationUtils.touchAnimate(secondDefectItem,secondDefectItem.getDrawingCacheBackgroundColor(),0xffcccccc,100);
                        break;
                    default:
                }
                return false;
            }
        });

        firstDefectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(defectTypeItem.getFirstType()=="电缆本体"||defectTypeItem.getFirstType()=="辅助设备"||defectTypeItem.getFirstType()=="电缆线路避雷器"){
                    Intent intent = new Intent(context, DefectTypeActivitySecond.class);
                    intent.putExtra("tower_info",towerInfo);
                    intent.putExtra("defect_type",defectTypeItem.getFirstType());
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, DefectFormActivity.class);
                    intent.putExtra("tower_info",towerInfo);
                    intent.putExtra("defect_type",defectTypeItem.getFirstType());
                    intent.putStringArrayListExtra("defect_content",defectTypeItem.getFirstSpinnerList());
                    context.startActivity(intent);

                }

            }
        });
        secondDefectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(defectTypeItem.getSecondType()=="电缆终端"||defectTypeItem.getSecondType()=="中间接头"||defectTypeItem.getSecondType()=="通道"){
                    Intent intent = new Intent(context, DefectTypeActivitySecond.class);
                    intent.putExtra("tower_info",towerInfo);
                    intent.putExtra("defect_type",defectTypeItem.getSecondType());
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, DefectFormActivity.class);
                    intent.putExtra("tower_info",towerInfo);
                    intent.putExtra("defect_type",defectTypeItem.getSecondType());
                    intent.putStringArrayListExtra("defect_content",defectTypeItem.getSecondSpinnerList());
                    context.startActivity(intent);
                }
            }
        });
    }

}
