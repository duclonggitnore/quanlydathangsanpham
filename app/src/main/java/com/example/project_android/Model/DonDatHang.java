package com.example.project_android.Model;

public class DonDatHang {
    String maDH, ngayDH, maKH;

    public DonDatHang(String maDH, String ngayDH, String maKH) {
        this.maDH = maDH;
        this.ngayDH = ngayDH;
        this.maKH = maKH;
    }

    public DonDatHang() {
        this.maDH = maDH;
        this.ngayDH = ngayDH;
        this.maKH = maKH;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getNgayDH() {
        return ngayDH;
    }

    public void setNgayDH(String ngayDH) {
        this.ngayDH = ngayDH;
    }

    public String getmaKH() {
        return maKH;
    }

    public void setmaKH(String maKH) {
        this.maKH = maKH;
    }

    @Override
    public String toString() {
        return "DonDatHang{" +
                "maDH='" + maDH + '\'' +
                ", ngayDH='" + ngayDH + '\'' +
                ", maKH='" + maKH + '\'' +
                '}';
    }
}
