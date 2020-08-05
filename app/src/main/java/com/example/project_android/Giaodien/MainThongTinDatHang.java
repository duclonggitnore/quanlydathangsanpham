package com.example.project_android.Giaodien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.project_android.Adapter.CustomAdapter_TTDDH;
import com.example.project_android.Database.DBDonDatHang;
import com.example.project_android.Database.DBThongTinDDH;
import com.example.project_android.Model.ThongTinDDH;
import com.example.project_android.R;

import java.util.ArrayList;

public class MainThongTinDatHang extends AppCompatActivity {
    Spinner spMaDH;
    TextView tvMaSP, tvSoLuong;

    Button btnThem, btnXoa, btnSua ,btnThoat;;

    ListView lvDSTTDH;

    ArrayList<ThongTinDDH> data_ttdh = new ArrayList<>();
    CustomAdapter_TTDDH adapter_ttdh;

    ArrayList<String> data_ddh = new ArrayList();
    ArrayAdapter adapter_ddh;

    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin_dat_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ShowData();
        loadSpinner();

        lvDSTTDH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThongTinDDH thongTinDDH = data_ttdh.get(position);
                spMaDH.setSelection(index);
                tvMaSP.setText(thongTinDDH.getMaSP());
                tvSoLuong.setText(thongTinDDH.getSoluong());
                index = position;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvMaSP.length() > 0 && tvSoLuong.length() > 0) {
                    ThongTinDDH thongTinDDH = getTTDH();
                    data_ttdh.add(thongTinDDH);
                    DBThongTinDDH dbThongTinDDH = new DBThongTinDDH(getApplicationContext());
                    dbThongTinDDH.ThemTTDDH(thongTinDDH);
                    adapter_ddh.notifyDataSetChanged();
                    Toast.makeText(MainThongTinDatHang.this, "Add succesfully!", Toast.LENGTH_LONG).show();
                } else {
                    if (tvSoLuong.length() == 0) {
                        tvSoLuong.setError("Vui lòng nhập lại");
                    }
                    if (tvMaSP.length() == 0) {
                        tvMaSP.setError("Vui lòng nhập lại");
                    }
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainThongTinDatHang.this);
                builder.setTitle("Notification");
                builder.setMessage("You are have delete ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_ttdh.remove(index);
                        ThongTinDDH thongTinDDH = getTTDH();
                        adapter_ttdh.notifyDataSetChanged();
                        DBThongTinDDH dbThongTinDDH = new DBThongTinDDH(getApplicationContext());
                        dbThongTinDDH.xoa(thongTinDDH);

                        tvMaSP.setText("");
                        tvSoLuong.setText("");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainThongTinDatHang.this);
                builder.setTitle("Notification!");
                builder.setMessage("You are update  ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThongTinDDH thongTinDDH = data_ttdh.get(index);
                        thongTinDDH.setmaDH(spMaDH.getSelectedItem().toString());
                        thongTinDDH.setMaSP(tvMaSP.getText().toString());
                        thongTinDDH.setSoluong(tvSoLuong.getText().toString());
                        adapter_ttdh.notifyDataSetChanged();
                        DBThongTinDDH dbThongTinDDH = new DBThongTinDDH(getApplicationContext());
                        thongTinDDH = getTTDH();
                        dbThongTinDDH.sua(thongTinDDH);

                        tvMaSP.setText("");
                        tvSoLuong.setText("");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainThongTinDatHang.this);
                builder.setTitle("Exit!!");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                androidx.appcompat.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    private ThongTinDDH getTTDH(){
        ThongTinDDH thongTinDDH = new ThongTinDDH();
        thongTinDDH.setmaDH(spMaDH.getSelectedItem().toString());
        thongTinDDH.setMaSP(tvMaSP.getText().toString());
        thongTinDDH.setSoluong(tvSoLuong.getText().toString());
        return thongTinDDH;
    }

    private void ShowData() {
        DBThongTinDDH dbThongTinDDH = new DBThongTinDDH(this);
        data_ttdh = dbThongTinDDH.getDataTTDH();
        adapter_ttdh = new CustomAdapter_TTDDH(this, R.layout.list_view_ttddh,data_ttdh);
        lvDSTTDH.setAdapter(adapter_ttdh);
    }

    private void loadSpinner() {
        DBDonDatHang dbDonDatHang = new DBDonDatHang(this);
        data_ddh = dbDonDatHang.LayMaDH();
        adapter_ddh = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,data_ddh);
        spMaDH.setAdapter(adapter_ddh);
    }

    private void setControl() {
        spMaDH = findViewById(R.id.spMaDH);
        tvMaSP = findViewById(R.id.maSP);
        tvSoLuong = findViewById(R.id.soluong);

        btnThem = findViewById(R.id.themTTDH);
        btnXoa = findViewById(R.id.xoaTTDH);
        btnSua = findViewById(R.id.suaTTDH);
        btnThoat = findViewById(R.id.Thoatttdh);
        lvDSTTDH = findViewById(R.id.lvTTDH);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.doc:
                Toast.makeText(getApplicationContext(),"You have arrived at the order information list",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),List_TTDH.class);
                startActivity(intent);
                break;
            case R.id.thoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainThongTinDatHang.this);
                builder.setTitle("Notification");
                builder.setMessage("You are have'n exit");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }
}
