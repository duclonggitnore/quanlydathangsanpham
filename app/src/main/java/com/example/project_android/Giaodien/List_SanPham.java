package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project_android.Adapter.CustomAdapter_SanPham;
import com.example.project_android.Database.DBSanPham;
import com.example.project_android.Model.SanPham;
import com.example.project_android.R;

import java.util.ArrayList;

public class List_SanPham extends AppCompatActivity {
    ListView lvSP;
    ArrayList<SanPham> dataSanPham = new ArrayList<>();
    CustomAdapter_SanPham adapter_sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__san_pham);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        DBSanPham dbSanPham = new DBSanPham(this);
        dataSanPham = dbSanPham.getDataSP();

        adapter_sanPham = new CustomAdapter_SanPham(this, R.layout.list_view_sanpham,dataSanPham);
        lvSP.setAdapter(adapter_sanPham);
    }

    private void setControl() {
        lvSP = findViewById(R.id.listSanPham);
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