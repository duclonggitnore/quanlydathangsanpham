package com.example.project_android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_android.Model.DonDatHang;

import java.util.ArrayList;

public class DBDonDatHang {
    DBHelper dbHelper;

    public DBDonDatHang(Context context){
        dbHelper = new DBHelper(context);
    }

    public void themDDH(DonDatHang donDatHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", donDatHang.getMaDH());
        values.put("ngayDH", donDatHang.getNgayDH());
        values.put("maKH", donDatHang.getmaKH());
        db.insert("DonDatHang", null, values);
    }

    public ArrayList<DonDatHang> getDataDDH() {
        ArrayList<DonDatHang> data = new ArrayList<>();
        String sql = "select * from DonDatHang";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            DonDatHang donDatHang = new DonDatHang();
            donDatHang.setMaDH(cursor.getString(0));
            donDatHang.setNgayDH(cursor.getString(1));
            donDatHang.setmaKH(cursor.getString(2));
            data.add(donDatHang);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<DonDatHang> getDataDDH(String ma) {
        ArrayList<DonDatHang> data = new ArrayList<>();
        String sql = "select * from DonDatHang where maDH = '"+ ma + "' ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            DonDatHang donDatHang = new DonDatHang();
            donDatHang.setMaDH(cursor.getString(0));
            donDatHang.setNgayDH(cursor.getString(1));
            donDatHang.setmaKH(cursor.getString(2));
            data.add(donDatHang);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<String> LayMaDH() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from DonDatHang ";
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

    public void xoa(DonDatHang donDatHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", donDatHang.getMaDH());
        values.put("ngayDH", donDatHang.getNgayDH());
        values.put("maKH", donDatHang.getmaKH());
        db.delete("DonDatHang", "maDH = '" + donDatHang.getMaDH() + "'", null);
    }

    public void sua(DonDatHang donDatHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", donDatHang.getMaDH());
        values.put("ngayDH", donDatHang.getNgayDH());
        values.put("maKH", donDatHang.getmaKH());
        db.update("DonDatHang", values, "maDH = '" + donDatHang.getMaDH() + "'", null);
    }
}
