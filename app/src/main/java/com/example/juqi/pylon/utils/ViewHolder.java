package com.example.juqi.pylon.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Lio_Zhuo on 2016/6/13 0013.
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;
    private final String imgPath = Environment.getExternalStorageDirectory() + "/DefectRecord/Cache";

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position){

        this.context = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position){
        if(convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }else{
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    //通过viewId获取控件
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view==null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    public ViewHolder setText(int viewId, String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }



    public ViewHolder setImageUrl(View progress, View image, View close, String imgName, final String imageUrl){
        ImageView progressView = (ImageView) progress;
        ImageView imageView = (ImageView) image;
        ImageView closeView = (ImageView)close;
        String imageName = imgName+".png";
        File f = new File(imgPath, imageName);
        if(!f.exists()){
            if(imageUrl == null ){
                progress.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                closeView.setVisibility(View.GONE);
                //Toast.makeText(context,"图片不存在",Toast.LENGTH_SHORT).show();
            }
            else if(imageUrl != imageName){
            }else {
                progress.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                closeView.setVisibility(View.GONE);
                Toast.makeText(context,"图片不存在或已损坏", Toast.LENGTH_SHORT).show();
            }

        }else{
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath+"/"+imageName);
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
            closeView.setVisibility(View.VISIBLE);

        }

        return this;
    }


}
