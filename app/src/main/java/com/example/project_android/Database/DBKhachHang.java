package com.example.project_android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_android.Model.KhachHang;

import java.util.ArrayList;

public class DBKhachHang {
    DBHelper dbHelper;
    public DBKhachHang(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void Them(KhachHang khachHang)
    {
        try{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("maKH", khachHang.getMaKH());
            values.put("tenKH", khachHang.getTenKH());
            values.put("diachi", khachHang.getDiachi());
            values.put("sdt", khachHang.getSdt());
            db.insert("KhachHang",null,values);
        }
        catch (Exception ex)
        {

        }
    }

    public void Xoa(KhachHang khachHang){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from KhachHang where maKH = '"+ khachHang.getMaKH()+"'";
        db.execSQL(sql);
    }

    public void Sua(KhachHang khachHang){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", khachHang.getMaKH());
        values.put("tenKH", khachHang.getTenKH());
        values.put("diachi", khachHang.getDiachi());
        values.put("sdt", khachHang.getSdt());
        db.update("KhachHang",values,"maKH ='"+ khachHang.getMaKH()+ "'",null);
    }

    public ArrayList<KhachHang> getData(){
        ArrayList<KhachHang> data = new ArrayList<>();
        String sql="select * from KhachHang";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do{
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(cursor.getString(0));
                khachHang.setTenKH(cursor.getString(1));
                khachHang.setDiachi(cursor.getString(2));
                khachHang.setSdt(cursor.getString(3));
                data.add(khachHang);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex){

        }
        return  data;
    }

    public ArrayList<KhachHang> getData(String ma){
        ArrayList<KhachHang> data = new ArrayList<>();
        String sql="select * from KhachHang where maKH = '"+ ma+"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try{
            cursor.moveToFirst();
            do{
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(cursor.getString(0));
                khachHang.setTenKH(cursor.getString(1));
                khachHang.setDiachi(cursor.getString(2));
                khachHang.setSdt(cursor.getString(3));
                data.add(khachHang);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex){

        }
        return data;
    }

    public ArrayList<String> LayMaKH() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from KhachHang ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {

                data.add(cursor.getString(0));
            }

            while (cursor.moveToNext());
        } catch (Exception ex) {

        }
        return data;
    }
}
