package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project_android.Adapter.CustomAdapter_DDH;
import com.example.project_android.Database.DBDonDatHang;
import com.example.project_android.Model.DonDatHang;
import com.example.project_android.R;
import java.util.ArrayList;

public class List_DDH extends AppCompatActivity {
    ListView lvDDH;
    ArrayList<DonDatHang> data_ddh = new ArrayList<>();
    CustomAdapter_DDH adapter_ddh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__d_d_h);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvDDH = findViewById(R.id.listTTDH);
    }

    private void setEvent() {
        DBDonDatHang dbDonDatHang = new DBDonDatHang(this);
        data_ddh = dbDonDatHang.getDataDDH();

        adapter_ddh = new CustomAdapter_DDH(this,R.layout.list_view_dondathang,data_ddh);
        lvDDH.setAdapter(adapter_ddh);
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