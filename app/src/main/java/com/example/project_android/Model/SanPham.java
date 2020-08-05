package com.example.project_android.Model;

public class SanPham {
    String maSP, tenSP, xuatxu,dongia;

    public SanPham(String maSP, String tenSP, String xuatxu, String dongia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.xuatxu = xuatxu;
        this.dongia = dongia;
    }

    public SanPham(){
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.xuatxu = xuatxu;
        this.dongia = dongia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getdongia() {
        return dongia;
    }

    public void setdongia(String dongia) {
        this.dongia = dongia;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", xuatxu='" + xuatxu + '\'' +
                ", dongia='" + dongia + '\'' +
                '}';
    }
}
