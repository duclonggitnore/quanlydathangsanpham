package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project_android.Adapter.CustomAdapter_KhachHang;
import com.example.project_android.Database.DBKhachHang;
import com.example.project_android.Model.KhachHang;
import com.example.project_android.R;

import java.util.ArrayList;

public class List_KhachHang extends AppCompatActivity {
    ListView lvDSKH;
    ArrayList<KhachHang> data_KH = new ArrayList<>();
    CustomAdapter_KhachHang adapter_khachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__khach_hang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        DBKhachHang dbKhachHang = new DBKhachHang(this);
        data_KH = dbKhachHang.getData();

        adapter_khachHang = new CustomAdapter_KhachHang(this,R.layout.list_view_khachhang,data_KH);
        lvDSKH.setAdapter(adapter_khachHang);
        registerForContextMenu(lvDSKH);
    }

    private void setControl() {
        lvDSKH = findViewById(R.id.listKhachHang);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}