package com.example.juqi.pylon.adapter;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juqi.pylon.R;
import com.example.juqi.pylon.utils.NumberFormatUtil;

public class InquireDefectHistoryAdapter extends SimpleCursorAdapter {
    private Cursor m_cursor;
    private Context m_context;
    private LayoutInflater mInflater;
    private ViewHolder vh;
    private final String filePath = Environment.getExternalStorageDirectory() + "/DefectRecord/Cache";
    public InquireDefectHistoryAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        m_cursor = c;
        m_context = context;
        mInflater = LayoutInflater.from(context);
        Log.d("database","构造函数");
    }

    /* 获取ListView中的Item 界面元素的函数
     一般的情况下，扩展定义好的XML界面布局文件,(返回的是bindView中的 view)
       return mInflater.inflate(R.layout.note_item, arg2, false);
       */

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        final View view = super.newView(context, cursor, parent);
        View vView = mInflater.inflate(R.layout.defect_history_inquire_record,parent,false);
        vView.setTag(new ViewHolder(vView));
        Log.d("database","123");
        return vView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        return super.getView(position, convertView, parent);
    }

     /*  说明： 绑定视图函数
     View 参数：当前ListView 的Item View，在View 中包含了ListView Item 界面元素,
     界面元素调用的方式为：TextView noteData = (TextView) view.findViewById(R.id.note_list_time);
     Cursor参数：当前游标根据Cursor来获取数据的方法:String ID= Cursor.getString(3)
     */

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        super.bindView(view, context, cursor);
        String isEliminated = cursor.getString(cursor.getColumnIndex("isDel"));
        vh = (ViewHolder) view.getTag();

        if(isEliminated.equals("是")){
            vh.inquireEliminationPersonLinear.setVisibility(View.VISIBLE);
            vh.inquireDiscoveryDateLinear.setVisibility(View.VISIBLE);
            vh.inquireGoneDivider1.setVisibility(View.VISIBLE);
            vh.inquireGoneDivider2.setVisibility(View.VISIBLE);

        }else {
            vh.inquireEliminationPersonLinear.setVisibility(View.GONE);
            vh.inquireDiscoveryDateLinear.setVisibility(View.GONE);
            vh.inquireGoneDivider1.setVisibility(View.GONE);
            vh.inquireGoneDivider2.setVisibility(View.GONE);
        }

        final String recordNum = "缺陷记录"+NumberFormatUtil.formatInteger(cursor.getInt(cursor.getColumnIndex("_id")));
        Log.d("database",recordNum);
        setViewText(vh.recordTitleView, recordNum);
        setViewText(vh.towerName,cursor.getString(cursor.getColumnIndex("lineName"))+cursor.getString(cursor.getColumnIndex("towerNum")));

        vh.imageDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = cursor.getString(cursor.getColumnIndex("image"));
                Bitmap bitmap = BitmapFactory.decodeFile(filePath+"/"+imageName);
                Log.d("database", imageName);
                if (bitmap != null) {
                    Log.d("database", recordNum+"存在"+imageName);
                    //setViewImage(vh.imageView,filePath+"/"+imageName);
                    vh.imageView.setImageBitmap(bitmap);
                    vh.inquireDefectPhotoLayout.setVisibility(View.VISIBLE);
                    vh.imageView.setVisibility(View.VISIBLE);
                    vh.inquireCloseImage.setVisibility(View.VISIBLE);
                    // imageDisplayButton.setVisibility(View.GONE);
                } else {
                    Log.d("database", "不存在"+imageName);
                    vh.imageDisplayButton.setVisibility(View.GONE);
                    vh.inquireCloseImage.setVisibility(View.GONE);
                    Toast.makeText(m_context,"图片不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vh.inquireCloseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    vh.inquireDefectPhotoLayout.setBackground(null);
                }
                vh.inquireDefectPhotoLayout.setVisibility(View.GONE);
            }
        });

    }

    private class ViewHolder {
        Button imageDisplayButton;
        ImageView imageView;
        TextView recordTitleView;
        TextView towerName;
        LinearLayout inquireEliminationPersonLinear;
        LinearLayout inquireDiscoveryDateLinear;
        View inquireGoneDivider1;
        View inquireGoneDivider2;
        RelativeLayout inquireDefectPhotoLayout;
        ImageView inquireCloseImage;

        ViewHolder(View v) {
            Log.d("database","233");
            recordTitleView = (TextView) v.findViewById(R.id.inquire_defect_id);
            towerName = (TextView) v.findViewById(R.id.inquire_defect_tower_name);
            imageView =(ImageView) v.findViewById(R.id.inquire_defect_photo);
            inquireEliminationPersonLinear = (LinearLayout) v.findViewById(R.id.inquire_elimination_person_linear);
            inquireDiscoveryDateLinear = (LinearLayout) v.findViewById(R.id.inquire_discovery_date_linear);
            inquireGoneDivider1 =(View) v.findViewById(R.id.inquire_gone_divider_1);
            inquireGoneDivider2 = (View) v.findViewById(R.id.inquire_gone_divider_2);
            inquireDefectPhotoLayout = (RelativeLayout) v.findViewById(R.id.inquire_defect_photo_layout);
            inquireCloseImage = (ImageView) v.findViewById(R.id.inquire_close_image);
        }
    }

}
