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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_android.Adapter.CustomAdapter_SanPham;
import com.example.project_android.Database.DBSanPham;
import com.example.project_android.Database.DBThongTinDDH;
import com.example.project_android.Model.SanPham;
import com.example.project_android.R;

import java.util.ArrayList;

public class MainSanPham extends AppCompatActivity {
    Spinner spMaSP;
    EditText etTenSP, etXuatxu, etDongia;
    Button btnThem, btnXoa, btnSua , btnThoat;
    ListView lvDSSP;

    ArrayList<SanPham> data_sp = new ArrayList<>();
    CustomAdapter_SanPham adapter_sanPham;

    ArrayList<String> data_MaSP = new ArrayList<>();
    ArrayAdapter adapter_MaSP;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_san_pham);
        setControl();
        setEvent();
    }

    private void setEvent() {
     ShowData();
       LoadSpinner();

        lvDSSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sanPham = data_sp.get(position);
                spMaSP.setSelection(index);
                etTenSP.setText(sanPham.getTenSP());
                etXuatxu.setText(sanPham.getXuatxu());
                etDongia.setText(sanPham.getdongia());
                index = position;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTenSP.length() > 0 && etXuatxu.length() > 0 && etDongia.length() > 0) {
                    SanPham sanPham = getSP();
                    data_sp.add(sanPham);
                    DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                    dbSanPham.Them(sanPham);
                   adapter_sanPham.notifyDataSetChanged();
                    Toast.makeText(MainSanPham.this, "Add succesfully!", Toast.LENGTH_LONG).show();

                    etTenSP.setText("");
                    etXuatxu.setText("");
                    etDongia.setText("");
                } else {
                    if (etTenSP.length() == 0) {
                        etTenSP.setError("please enter again!");
                    }
                    if (etXuatxu.length() == 0) {
                        etXuatxu.setError("Please enter again!");
                    }
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainSanPham.this);
                builder.setTitle("Notification!");
                builder.setMessage("You have'n delete ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_sp.remove(index);
                        SanPham sanPham = getSP();
                        adapter_sanPham.notifyDataSetChanged();
                        DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                        dbSanPham.xoa(sanPham);

                        etTenSP.setText("");
                        etXuatxu.setText("");
                        etDongia.setText("");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainSanPham.this);
                builder.setTitle("Notification1");
                builder.setMessage("You have update ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SanPham sanPham = data_sp.get(index);
                        sanPham.setMaSP(spMaSP.getSelectedItem().toString());
                        sanPham.setTenSP(etTenSP.getText().toString());
                        sanPham.setXuatxu(etXuatxu.getText().toString());
                        sanPham.setdongia(etDongia.getText().toString());
                        adapter_sanPham.notifyDataSetChanged();
                        DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                        sanPham = getSP();
                        dbSanPham.sua(sanPham);

                        etTenSP.setText("");
                        etXuatxu.setText("");
                        etDongia.setText("");
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
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainSanPham.this);
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

    private void ShowData() {
        DBSanPham dbSanPham = new DBSanPham(this);
        data_sp = dbSanPham.getDataSP();
        adapter_sanPham = new CustomAdapter_SanPham(this, R.layout.list_view_sanpham,data_sp);
        lvDSSP.setAdapter(adapter_sanPham);
    }

    private void LoadSpinner() {
        DBThongTinDDH dbThongTinDDH = new DBThongTinDDH(this);
        data_MaSP = dbThongTinDDH.LayMaSP();
        adapter_MaSP = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,data_MaSP);
        spMaSP.setAdapter(adapter_MaSP);
    }

    private SanPham getSP(){
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(spMaSP.getSelectedItem().toString());
        sanPham.setTenSP(etTenSP.getText().toString());
        sanPham.setXuatxu(etXuatxu.getText().toString());
        sanPham.setdongia(etDongia.getText().toString());
        return sanPham;
    }

    private void setControl() {
        spMaSP = findViewById(R.id.spMaSP);
        etTenSP = findViewById(R.id.tenSP);
        etXuatxu = findViewById(R.id.xuatxu);
        etDongia = findViewById(R.id.dongia);

        btnThem = findViewById(R.id.themSP);
        btnXoa = findViewById(R.id.xoaSP);
        btnSua = findViewById(R.id.suaSP);
        btnThoat = findViewById(R.id.Thoatsp);

        lvDSSP = findViewById(R.id.lvDSSP);
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
                Toast.makeText(getApplicationContext(),"You have arrived at the product list!.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),List_SanPham.class);
                startActivity(intent);
                break;
            case R.id.thoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainSanPham.this);
                builder.setTitle("Notification!");
                builder.setMessage("You have exit?");
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
