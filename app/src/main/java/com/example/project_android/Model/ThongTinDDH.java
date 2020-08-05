package com.example.project_android.Model;

public class ThongTinDDH {
    String maDH, maSP, soluong;

    public ThongTinDDH(String maDH, String maSP, String soluong) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.soluong = soluong;
    }

    public ThongTinDDH() {
        this.maDH = maDH;
        this.maSP = maSP;
        this.soluong = soluong;
    }

    public String getmaDH() {
        return maDH;
    }

    public void setmaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "ThongTinDDH{" +
                "maDH='" + maDH + '\'' +
                ", maSP='" + maSP + '\'' +
                ", soluong='" + soluong + '\'' +
                '}';
    }
}
