package com.example.project_android.Database;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.project_android.Model.ThongTinDDH;

import java.util.ArrayList;

public class DBThongTinDDH {
    DBHelper dbHelper;

    public DBThongTinDDH(Context context){
        dbHelper = new DBHelper(context);
    }

    public void ThemTTDDH(ThongTinDDH thongTinDDH)
    {
        try{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("maDH", thongTinDDH.getmaDH());
            values.put("maSP", thongTinDDH.getMaSP());
            values.put("soluong", thongTinDDH.getSoluong());
            db.insert("ThongTinDDH",null,values);
        }
        catch (Exception ex)
        {

        }
    }

    public void xoa(ThongTinDDH thongTinDDH) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", thongTinDDH.getmaDH());
        values.put("maSP", thongTinDDH.getMaSP());
        values.put("soluong", thongTinDDH.getSoluong());
        db.delete("ThongTinDDH", "maDH = '" + thongTinDDH.getmaDH() + "'", null);
    }

    public void sua(ThongTinDDH thongTinDDH) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", thongTinDDH.getmaDH());
        values.put("maSP", thongTinDDH.getMaSP());
        values.put("soluong", thongTinDDH.getSoluong());
        db.update("ThongTinDDH", values, "maDH = '" + thongTinDDH.getmaDH() + "'", null);
    }

    public ArrayList<ThongTinDDH> getDataTTDH(){
        ArrayList<ThongTinDDH> data = new ArrayList<>();
        String sql="select * from ThongTinDDH";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery(sql,null);

        try{
            cursor.moveToFirst();
            do{
                ThongTinDDH thongTinDDH = new ThongTinDDH();
                thongTinDDH.setmaDH(cursor.getString(0));
                thongTinDDH.setMaSP(cursor.getString(1));
                thongTinDDH.setSoluong(cursor.getString(2));
                data.add(thongTinDDH);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex){

        }
        return  data;
    }

    public ArrayList<ThongTinDDH> getDataTTDH(String ma){
        ArrayList<ThongTinDDH> data = new ArrayList<>();
        String sql="select * from ThongTinDDH where maDH = '"+ ma+"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try{
            cursor.moveToFirst();
            do{
                ThongTinDDH thongTinDDH = new ThongTinDDH();
                thongTinDDH.setmaDH(cursor.getString(0));
                thongTinDDH.setMaSP(cursor.getString(1));
                thongTinDDH.setSoluong(cursor.getString(2));
                data.add(thongTinDDH);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex){

        }
        return data;
    }

    public ArrayList<String> LayMaSP() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from ThongTinDDH ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                data.add(cursor.getString(1));
            }

            while (cursor.moveToNext());
        } catch (Exception ex) {

        }
        return data;
    }

}
