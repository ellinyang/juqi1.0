package com.example.juqi.pylon.adapter;

import android.content.Context;
import android.widget.SpinnerAdapter;

import com.example.juqi.pylon.utils.ViewHolder;

import java.util.List;

/**
 * Created by Lio_Zhuo on 2016/6/24 0024.
 */
public class DefectSpinnerAdapter extends CommonAdapter<String> implements SpinnerAdapter {

    public DefectSpinnerAdapter(Context context, List<String> data){
        super(context,data,android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void convert(final ViewHolder holder, final String spinnerValue) {
        holder.setText(android.R.id.text1,spinnerValue);
    }
}
