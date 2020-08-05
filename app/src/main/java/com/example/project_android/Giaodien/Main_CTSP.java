package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.project_android.Database.DBSanPham;
import com.example.project_android.Model.SanPham;
import com.example.project_android.R;

import java.util.ArrayList;

public class Main_CTSP extends AppCompatActivity {
    EditText spMaSP, etTenSP, etXuatxu, etDongia;
    ArrayList<SanPham> data_sp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__c_t_s_p);
        seControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("maSP");
        DBSanPham dbSanPham = new DBSanPham(this);
        data_sp = dbSanPham.getDataSP(ma);
        spMaSP.setText("Mã Sản Phẩm: "+data_sp.get(0).getMaSP());
        etTenSP.setText("Tên Sản Phẩm: "+data_sp.get(0).getTenSP());
        etXuatxu.setText("Xuất xứ: "+data_sp.get(0).getXuatxu());
        etDongia.setText("Đơn giá: "+data_sp.get(0).getdongia());
    }

    private void seControl() {
        spMaSP = findViewById(R.id.spMaSP);
        etTenSP = findViewById(R.id.tenSP);
        etXuatxu = findViewById(R.id.xuatxu);
        etDongia = findViewById(R.id.dongia);
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