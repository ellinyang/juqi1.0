package com.example.juqi.pylon.adapter;

import android.content.Context;

import com.example.juqi.pylon.R;
import com.example.juqi.pylon.entity.TowerInfoItem;
import com.example.juqi.pylon.utils.ViewHolder;

import java.util.List;

/**
 * Created by Lio_Zhuo on 2016/5/31 0031.
 */
public class TowerInfoAdapter extends CommonAdapter<TowerInfoItem> {

    public TowerInfoAdapter(Context context, List<TowerInfoItem> data){
        super(context,data,R.layout.tower_info_item);
    }

    @Override
    public void convert(final ViewHolder holder,final TowerInfoItem towerInfoItem){
        holder.setText(R.id.tower_info_name,towerInfoItem.getTowerInfoName()).
                setText(R.id.tower_info_value,towerInfoItem.getTowerInfoValue());
    }

}
