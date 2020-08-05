package com.example.project_android.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "QuanLyDonHangSanPham", null, 1);
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    private Cursor Get_Data(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlKH ="create table KhachHang(maKH text, tenKH text, diachi text, sdt text)";
        String sqlDDH ="create table DonDatHang(maDH text, ngayDH text, maKH text)";
        String sqlTTDDH = "create table ThongTinDDH(maDH text, maSP text, soluong text)";
        String sqlSP = "create table SanPham(maSP text, tenSP text, xuatxu text, dongia text)";

        db.execSQL(sqlKH);
        db.execSQL(sqlDDH);
        db.execSQL(sqlTTDDH);
        db.execSQL(sqlSP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS KhachHang");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS DonDatHang");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS ThongTinDDH");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        onCreate(db);
    }
}
