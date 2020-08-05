package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.project_android.Database.DBThongTinDDH;
import com.example.project_android.Model.ThongTinDDH;
import com.example.project_android.R;

import java.util.ArrayList;

public class Main_CTTTDDH extends AppCompatActivity {
    EditText etMaDH, etMaSP, etSoluong;
    ArrayList<ThongTinDDH> data_ttdh = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__c_t_t_t_d_d_h);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("maDH");
        DBThongTinDDH dbThongTinDDH = new DBThongTinDDH(this);
        data_ttdh = dbThongTinDDH.getDataTTDH(ma);
        etMaDH.setText("Mã sản phẩm: "+data_ttdh.get(0).getmaDH());
        etMaSP.setText("Mã sản phẩm "+data_ttdh.get(0).getMaSP());
        etSoluong.setText("Số lượng: "+data_ttdh.get(0).getSoluong());
    }

    private void setControl() {
        etMaDH = findViewById(R.id.maDH);
        etMaSP = findViewById(R.id.maSP);
        etSoluong = findViewById(R.id.soluong);
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