package com.example.project_android.Model;

public class KhachHang {
    String maKH, tenKH, diachi, sdt;

    public KhachHang(String maKH, String tenKH, String diachi, String sdt) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public KhachHang() {

    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH='" + maKH + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", diachi='" + diachi + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
