package com.example.juqi.pylon.database;

/**
 * Created by ylj_y on 2016/11/5.
 */

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.juqi.pylon.entity.DefectHistoryItem;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {

    private WritableWorkbook wwb;
    private WritableSheet sheet;
    private WritableWorkbook wwb1;
    private WritableSheet sheet1;
    private Context mContext;
    private List<DefectHistoryItem> data;
    private String[] title = { "序号", "线路名", "塔号", "缺陷类型", "缺陷等级", "缺陷内容", "记录日期", "记录人员", "是否消缺", "消缺人员", "isPMS", "电压等级","消缺时间" };

    public ExcelUtil(Context context, List<DefectHistoryItem> defectHistoryItems) {

        mContext = context;
        data = defectHistoryItems;
    }

    public void ExportToExcel(String defectFileName) throws IOException, WriteException {

        /**输出的excel文件的路径*/
        String filePath = Environment.getExternalStorageDirectory().toString();
        String fileName = defectFileName+"_历史记录"+".xls";
        Log.d("excel","filePath:"+filePath);
        Log.d("excel","fileName:"+fileName);
        File file = new File(filePath, fileName);
        if (!file.exists()) {
            file.createNewFile();
            Log.d("excel","createFile");
        }
        // 创建Excel工作表
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        sheet = wwb.createSheet("缺陷记录", 0);
        sheet.getSettings().setDefaultColumnWidth(13);//给sheet电子版中所有的列设置默认的列的宽度;
        //sheet.getSettings().setDefaultRowHeight(800);//给sheet电子版中所有的行设置默认的行的宽度;
        sheet.setRowView(0,500);//设置行高
        Log.d("excel","createSheet");
        Label label;
        // format();
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnView(i,13);//设置列宽
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label = new Label(i, 0, title[i],getHeader());
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }
        //合并第十四列第一行到第十七列第一行的所有单元格
        sheet.mergeCells(13,0,16,0);

        Log.d("excel","addCellTitle");
        Log.d("excel", String.valueOf(data.size()));

        for (int i = 0; i < data.size(); i++) {
            sheet.setRowView(i+1,4500); //设置行高
            //合并第十四列第i+1行到第十七列第i+1行的所有单元格
            sheet.mergeCells(13,i+1,16,i+1);
            DefectHistoryItem defectHistoryItem = data.get(i);
            jxl.write.Number idLabel = new jxl.write.Number(0, i + 1, defectHistoryItem.getId(),getCell());
            Log.d("excel", "id:"+ String.valueOf(defectHistoryItem.getId()));
            Label lineNameLabel = new Label(1, i + 1, defectHistoryItem.getLineName(),getCell());
            Label towerNumLabel = new Label(2, i + 1, String.valueOf(defectHistoryItem.getTowerNum()),getCell());
            Log.d("excel", "towerNum"+ String.valueOf(defectHistoryItem.getTowerNum()));
            Label defectTypeLabel = new Label(3, i + 1, defectHistoryItem.getDefectType(),getCell());
            Label defectLevelLabel = new Label(4, i + 1, defectHistoryItem.getDefectLevel(),getCell());
            Label contentLabel = new Label(5, i + 1,defectHistoryItem.getContent(),getCell());
            Label discoveryDateLabel = new Label(6, i + 1, defectHistoryItem.getRecordTime(),getCell());
            Label recordPersonLabel = new Label(7, i + 1, defectHistoryItem.getRecordPerson(),getCell());
            Label isDelLabel = new Label(8, i + 1,(defectHistoryItem.getIsDel()==true ? "是":"否"),getCell());
            Label delPersonLabel = new Label(9, i + 1, defectHistoryItem.getDelPerson(),getCell());
            Label isPMSLabel = new Label(10, i + 1, (defectHistoryItem.getIsPMS()==true ? "是":"否"),getCell());
            Label voltageLevelLabel = new Label(11 , i + 1,defectHistoryItem.getVoltageLevel(),getCell());
            Label delTimeLabel = new Label(12, i + 1, defectHistoryItem.getDelTime(),getCell());

            //String defectImageName = defectHistoryItem.getPicUrl();

            try {
                sheet.addCell(idLabel);
                sheet.addCell(lineNameLabel);
                sheet.addCell(towerNumLabel);
                sheet.addCell(defectTypeLabel);
                sheet.addCell(defectLevelLabel);
                sheet.addCell(contentLabel);
                sheet.addCell(discoveryDateLabel);
                sheet.addCell(recordPersonLabel);
                sheet.addCell(isDelLabel);
                sheet.addCell(delPersonLabel);
                sheet.addCell(isPMSLabel);
                sheet.addCell(voltageLevelLabel);
                sheet.addCell(delTimeLabel);
                //sheet.addCell((WritableCell) image);

            } catch (WriteException e) {
                e.printStackTrace();
            }
        }

        wwb.write();// 写入数据
        wwb.close();// 关闭文件

    /*    for (int i = 0; i < data.size(); i++) {
            DefectHistoryItem defectHistoryItem = data.get(i);
            String defectImageName = defectHistoryItem.getPicUrl();
            File imageFile = new File(filePath,defectImageName);
            if(imageFile.exists()){
                imageFile.delete();
                Log.d("excel","deleteImage");
            }
        }
*/
        //rescanFolder(filePath);
        // Toast.makeText(mContext, "导出完毕！", Toast.LENGTH_SHORT).show();
        Log.v("导出数据", "导出完毕！");
        Toast.makeText(mContext, "记录导出成功", Toast.LENGTH_LONG).show();
    }

    public void urlExportToExcel(String defectFileName) throws IOException, WriteException {
        /**输出的excel文件的路径*/
        String filePath = Environment.getExternalStorageDirectory() + "/DefectRecord";
        String fileName = defectFileName+"_历史记录_"+new Date().toLocaleString()+".xls";

        File file = new File(filePath, fileName);
        if (!file.exists()) {
            file.createNewFile();
            Log.d("excel","createFile");
        }
        // 创建Excel工作表
        OutputStream os = new FileOutputStream(file);
        wwb1 = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        sheet1 = wwb1.createSheet("缺陷记录", 0);
        sheet1.getSettings().setDefaultColumnWidth(13);//给sheet电子版中所有的列设置默认的列的宽度;
        //sheet.getSettings().setDefaultRowHeight(800);//给sheet电子版中所有的行设置默认的行的宽度;
        sheet1.setRowView(0,500);//设置行高
        Log.d("excel","createSheet");
        Label label;
        // format();
        for (int i = 0; i < title.length; i++) {
            sheet1.setColumnView(i,13);//设置列宽
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label = new Label(i, 0, title[i],getHeader());
            // 将定义好的单元格添加到工作表中
            sheet1.addCell(label);
        }
        //合并第十四列第一行到第十七列第一行的所有单元格
        sheet1.mergeCells(13,0,16,0);

        Log.d("excel","addCellTitle");
        Log.d("excel", String.valueOf(data.size()));

        for (int i = 0; i < data.size(); i++) {

            sheet1.setRowView(i+1,4500); //设置行高
            //合并第十四列第i+1行到第十七列第i+1行的所有单元格
            sheet1.mergeCells(13,i+1,16,i+1);

            DefectHistoryItem defectHistoryItem = data.get(i);
            jxl.write.Number idLabel = new jxl.write.Number(0, i + 1, defectHistoryItem.getId(),getCell());
            Log.d("excel", "id:"+ String.valueOf(defectHistoryItem.getId()));
            Label lineNameLabel = new Label(1, i + 1, defectHistoryItem.getLineName(),getCell());
            Label towerNumLabel = new Label(2, i + 1, String.valueOf(defectHistoryItem.getTowerNum()),getCell());
            Log.d("excel", "towerNum"+ String.valueOf(defectHistoryItem.getTowerNum()));
            Label defectTypeLabel = new Label(3, i + 1, defectHistoryItem.getDefectType(),getCell());
            Label defectLevelLabel = new Label(4, i + 1, defectHistoryItem.getDefectLevel(),getCell());
            Label contentLabel = new Label(5, i + 1,defectHistoryItem.getContent(),getCell());
            Label discoveryDateLabel = new Label(6, i + 1, defectHistoryItem.getRecordTime(),getCell());
            Label recordPersonLabel = new Label(7, i + 1, defectHistoryItem.getRecordPerson(),getCell());
            Label isDelLabel = new Label(8, i + 1,(defectHistoryItem.getIsDel()==true?"是":"否"),getCell());
            Label delPersonLabel = new Label(9, i + 1, defectHistoryItem.getDelPerson(),getCell());
            Label isPMSLabel = new Label(10, i + 1, (defectHistoryItem.getIsPMS()==true?"是":"否"),getCell());
            Label voltageLevelLabel = new Label(11 , i + 1,defectHistoryItem.getVoltageLevel(),getCell());
            Label delTimeLabel = new Label(12, i + 1, defectHistoryItem.getDelTime(),getCell());

            //获取图片地址
            String imgUrl = defectHistoryItem.getLineName()+defectHistoryItem.getTowerNum()+"塔"+"_"+defectHistoryItem.getDefectType()+"_"+defectHistoryItem.getContent()+"_"+defectHistoryItem.getRecordTime();
            Log.d("export","获取图片名字");
            File imageFile1 = new File(filePath+"/Cache",imgUrl+".png");
            if(imageFile1.exists()){
                WritableImage ri=new WritableImage(13,i+1,4,1,imageFile1);
                Log.d("export","包装图片");
                sheet1.addImage(ri);
            }
            try {
                sheet1.addCell(idLabel);
                sheet1.addCell(lineNameLabel);
                sheet1.addCell(towerNumLabel);
                sheet1.addCell(defectTypeLabel);
                sheet1.addCell(defectLevelLabel);
                sheet1.addCell(contentLabel);
                sheet1.addCell(discoveryDateLabel);
                sheet1.addCell(recordPersonLabel);
                sheet1.addCell(isDelLabel);
                sheet1.addCell(delPersonLabel);
                sheet1.addCell(isPMSLabel);
                sheet1.addCell(voltageLevelLabel);
                sheet1.addCell(delTimeLabel);

                Log.d("export","加入图片");
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
        // WritableImage ri=new WritableImage(4,14,5,5,new File(filePath,"200.png"));
        //sheet.addImage(ri);
        wwb1.write();// 写入数据
        Log.d("export","写入数据");
        wwb1.close();// 关闭文件
        Log.d("export","关闭文件");
        //rescanFolder(filePath);
        // Toast.makeText(mContext, "导出完毕！", Toast.LENGTH_SHORT).show();
        Log.v("导出数据", "导出完毕！");
        Log.d("export","导出完毕！");
        Toast.makeText(mContext, "写入成功", Toast.LENGTH_LONG).show();
    }


    //设置列名单元格式
    public WritableCellFormat getHeader() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 16,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLACK);// 黑色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }


    //设置单位格格式
    public WritableCellFormat getCell() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 10);// 定义字体
        try {
            font.setColour(Colour.BLACK);// 黑色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            //format.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);//自动换行
            format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

//    //扫描文件夹，更新文件夹目录
//    private void rescanFolder(String dest) {
//        // Scan files only (not folders);
//        File[] files = new File(dest).listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isFile();
//            }
//        });
//
//        String[] paths = new String[files.length];
//        for (int co=0; co< files.length; co++)
//            paths[co] = files[co].getAbsolutePath();
//
//        MediaScannerConnection.scanFile(mContext, paths, null, null);
//        Log.v("扫描文件", "扫描文件！");
//        // and now recursively scan subfolders
//        files = new File(dest).listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isDirectory();
//            }
//        });
//
//        for (int co=0; co<files.length; co++)
//            rescanFolder(files[co].getAbsolutePath());
//        Log.v("扫描文件", "扫描文件夹！");
//    }

}
