package com.example.project_android.Giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project_android.Adapter.CustomAdapter_TTDDH;
import com.example.project_android.Database.DBThongTinDDH;
import com.example.project_android.Model.ThongTinDDH;
import com.example.project_android.R;

import java.util.ArrayList;

public class List_TTDH extends AppCompatActivity {

    ListView lvTTDH;
    ArrayList<ThongTinDDH> data_ttdh = new ArrayList<>();
    CustomAdapter_TTDDH adapter_ttddh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__t_t_d_h);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        DBThongTinDDH dbThongTinDDH = new DBThongTinDDH(this);
        data_ttdh = dbThongTinDDH.getDataTTDH();

        adapter_ttddh = new CustomAdapter_TTDDH(this,R.layout.list_view_ttddh,data_ttdh);
        lvTTDH.setAdapter(adapter_ttddh);
        registerForContextMenu(lvTTDH);
    }

    private void setControl() {
        lvTTDH = findViewById(R.id.listTTDH);
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