package com.example.project_android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_android.Model.KhachHang;
import com.example.project_android.Model.SanPham;

import java.util.ArrayList;

public class DBSanPham {
    DBHelper dbHelper;

    public DBSanPham(Context context){
        dbHelper = new DBHelper(context);
    }

    public void Them(SanPham sanPham)
    {
        try{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("maSP", sanPham.getMaSP());
            values.put("tenSP", sanPham.getTenSP());
            values.put("xuatxu", sanPham.getXuatxu());
            values.put("dongia", sanPham.getdongia());
            db.insert("SanPham",null,values);
        }
        catch (Exception ex)
        {

        }
    }

    public void xoa(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSP", sanPham.getMaSP());
        values.put("tenSP", sanPham.getTenSP());
        values.put("xuatxu", sanPham.getXuatxu());
        values.put("dongia", sanPham.getdongia());
        db.delete("SanPham", "maSP = '" + sanPham.getMaSP() + "'", null);
    }

    public void sua(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSP",sanPham.getMaSP());
        values.put("tenSP",sanPham.getTenSP());
        values.put("xuatxu",sanPham.getXuatxu());
        values.put("dongia",sanPham.getdongia());
        db.update("SanPham", values, "maSP = '" + sanPham.getMaSP() + "'", null);
    }

    public ArrayList<SanPham> getDataSP() {
        ArrayList<SanPham> data = new ArrayList<>();
        String sql = "select * from SanPham";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(cursor.getString(0));
            sanPham.setTenSP(cursor.getString(1));
            sanPham.setXuatxu(cursor.getString(2));
            sanPham.setdongia(cursor.getString(3));
            data.add(sanPham);
        }
        while (cursor.moveToNext());
        return data;
    }

    public ArrayList<SanPham> getDataSP(String ma) {
        ArrayList<SanPham> data = new ArrayList<>();
        String sql = "select * from SanPham where maSP = '"+ ma + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(cursor.getString(0));
            sanPham.setTenSP(cursor.getString(1));
            sanPham.setXuatxu(cursor.getString(2));
            sanPham.setdongia(cursor.getString(3));
            data.add(sanPham);
        }
        while (cursor.moveToNext());
        return data;
    }
}
