package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.project_android.Database.DBKhachHang;
import com.example.project_android.Model.KhachHang;
import com.example.project_android.R;

import java.util.ArrayList;

public class Main_CTKH extends AppCompatActivity {
    EditText etMaKH, etTenKH, etDiachi, etSdt;
    ArrayList<KhachHang> data_kh = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__c_t_k_h);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("maKH");
        DBKhachHang dbKhachHang = new DBKhachHang(this);
        data_kh = dbKhachHang.getData(ma);
        etMaKH.setText("Mã Khách Hàng: "+data_kh.get(0).getMaKH());
        etTenKH.setText("Tên Khách Hàng: "+data_kh.get(0).getTenKH());
        etDiachi.setText("Địa chỉ: "+data_kh.get(0).getDiachi());
        etSdt.setText("SDT: "+data_kh.get(0).getSdt());

    }

    private void setControl() {
        etMaKH = findViewById(R.id.maKH);
        etTenKH = findViewById(R.id.tenKH);
        etDiachi = findViewById(R.id.diachi);
        etSdt = findViewById(R.id.sdt);
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