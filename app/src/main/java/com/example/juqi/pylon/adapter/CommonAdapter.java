package com.example.juqi.pylon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.juqi.pylon.utils.ViewHolder;

import java.util.List;

/**
 * Created by Lio_Zhuo on 2016/6/13 0013.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflater;
    protected int mLayoutId;
    protected ViewHolder mHolder;

    public CommonAdapter(Context context, List<T> data, int layoutId){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    public CommonAdapter(Context context, List<T> data){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getCount()
    {
        return mData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mData.get(position);
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = ViewHolder.get(mContext,convertView,parent, mLayoutId,position);
        this.mHolder = holder;
        convert(holder, (T) getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder,T t);


}
