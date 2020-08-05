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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project_android.Adapter.CustomAdapter_KhachHang;
import com.example.project_android.Database.DBKhachHang;
import com.example.project_android.Model.KhachHang;
import com.example.project_android.R;

import java.util.ArrayList;

public class MainKhachHang extends AppCompatActivity {

    EditText etMaKH, etTenKH, etDiaChi, etSDT;
    Button btnThem, btnXoa, btnSua, btnThoat;
    ListView lvDSKH;


    CustomAdapter_KhachHang adapter;
    ArrayList<KhachHang> data_kh = new ArrayList<>();

    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_khach_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ShowData();

        lvDSKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhachHang khachHang = data_kh.get(position);
                etMaKH.setText(khachHang.getMaKH());
                etTenKH.setText(khachHang.getTenKH());
                etDiaChi.setText(khachHang.getDiachi());
                etSDT.setText(khachHang.getSdt());
                index = position;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhachHang khachHang = getKhachHang();
                data_kh.add(khachHang);
                adapter.notifyDataSetChanged();
                DBKhachHang dbKhachHang = new DBKhachHang(getApplicationContext());
                dbKhachHang.Them(khachHang);

                etMaKH.setText("");
                etTenKH.setText("");
                etDiaChi.setText("");
                etSDT.setText("");

            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainKhachHang.this);
                builder.setTitle("Notification!!!");
                builder.setMessage("You have exit ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_kh.remove(index);
                        KhachHang khachHang = getKhachHang();
                        adapter.notifyDataSetChanged();
                        DBKhachHang dbKhachHang = new DBKhachHang(getApplicationContext());
                        dbKhachHang.Xoa(khachHang);

                        etMaKH.setText("");
                        etTenKH.setText("");
                        etDiaChi.setText("");
                        etSDT.setText("");
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MainKhachHang.this);
                builder.setTitle("Notification!!");
                builder.setMessage("You have update ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KhachHang khachHang = data_kh.get(index);
                        khachHang.setMaKH(etMaKH.getText().toString());
                        khachHang.setTenKH(etTenKH.getText().toString());
                        khachHang.setDiachi(etDiaChi.getText().toString());
                        khachHang.setSdt(etSDT.getText().toString());
                        adapter.notifyDataSetChanged();
                        DBKhachHang dbKhachHang = new DBKhachHang(getApplicationContext());
                        khachHang = getKhachHang();
                        dbKhachHang.Sua(khachHang);

                        etMaKH.setText("");
                        etTenKH.setText("");
                        etDiaChi.setText("");
                        etSDT.setText("");
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
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainKhachHang.this);
                builder.setTitle("Exit!!");
                builder.setMessage("Are you exit?");
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

    private KhachHang getKhachHang(){
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH(etMaKH.getText().toString());
        khachHang.setTenKH(etTenKH.getText().toString());
        khachHang.setDiachi(etDiaChi.getText().toString());
        khachHang.setSdt(etSDT.getText().toString());
        return khachHang;
    }

    private void ShowData() {
        DBKhachHang dbKhachHang = new DBKhachHang(this);
        data_kh = dbKhachHang.getData();
        adapter = new CustomAdapter_KhachHang(this,R.layout.list_view_khachhang,data_kh);
        lvDSKH.setAdapter(adapter);
    }

    private void setControl() {
        etMaKH = findViewById(R.id.maKH);
        etTenKH = findViewById(R.id.tenKH);
        etDiaChi = findViewById(R.id.diachi);
        etSDT = findViewById(R.id.sdt);

        btnThem = findViewById(R.id.Them);
        btnXoa = findViewById(R.id.Xoa);
        btnSua = findViewById(R.id.Sua);
        btnThoat = findViewById(R.id.Thoatkh);

        lvDSKH = findViewById(R.id.lvDSKH);
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
                Toast.makeText(getApplicationContext(), "You have just arrived at the customer list!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), List_KhachHang.class);
                startActivity(intent);
                break;

            case R.id.thoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainKhachHang.this);
                builder.setTitle("Notification!!!");
                builder.setMessage("You have exit ?");
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
