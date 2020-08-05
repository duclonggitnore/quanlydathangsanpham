package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.project_android.Database.DBDonDatHang;
import com.example.project_android.Model.DonDatHang;
import com.example.project_android.R;

import java.util.ArrayList;

public class Main_CTDDH extends AppCompatActivity {
    EditText etMaDH, etNgayDH, etMaKH;
    ArrayList<DonDatHang> data_ddh = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__c_t_d_d_h);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("maDH");
        DBDonDatHang dbDonDatHang = new DBDonDatHang(this);
        data_ddh = dbDonDatHang.getDataDDH(ma);
        etMaDH.setText("Mã đơn hàng: "+data_ddh.get(0).getMaDH());
        etNgayDH.setText("Ngày đặt hàng: "+data_ddh.get(0).getNgayDH());
        etMaKH.setText("Mã khách hàng: "+data_ddh.get(0).getmaKH());
    }

    private void setControl() {
        etMaDH = findViewById(R.id.maDH);
        etNgayDH = findViewById(R.id.ngayDH);
        etMaKH = findViewById(R.id.maKH);
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