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

import com.example.project_android.Adapter.CustomAdapter_DDH;
import com.example.project_android.Database.DBDonDatHang;
import com.example.project_android.Database.DBKhachHang;
import com.example.project_android.Model.DonDatHang;
import com.example.project_android.R;

import java.util.ArrayList;

public class MainDonDatHang extends AppCompatActivity {
    EditText etMaDH, etNgayDH;
    Spinner spMaKH;
    Button btnThem, btnXoa, btnSua, btnThoat;;
    ListView lvDanhSachDDH;

    ArrayList<DonDatHang> data_ddh = new ArrayList<>();
    CustomAdapter_DDH adapter_ddh;

    ArrayList<String> data_MaKH = new ArrayList<>();
    ArrayAdapter  adapter_MaKH;

    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_don_dat_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
       ShowData();
        LoadSpinner();

        lvDanhSachDDH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DonDatHang donDatHang = data_ddh.get(position);
                etMaDH.setText(donDatHang.getMaDH());
                etNgayDH.setText(donDatHang.getNgayDH());
                spMaKH.setSelection(index);
                index = position;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMaDH.length() > 0 && etNgayDH.length() > 0) {
                    DonDatHang donDatHang = getDDH();
                    data_ddh.add(donDatHang);
                    DBDonDatHang dbDonDatHang = new DBDonDatHang(getApplicationContext());
                    dbDonDatHang.themDDH(donDatHang);
                    adapter_ddh.notifyDataSetChanged();
                    Toast.makeText(MainDonDatHang.this, "Add succesfully!", Toast.LENGTH_LONG).show();
                } else {
                    if (etMaDH.length() == 0 && etNgayDH.length() == 0) {
                        etMaDH.setError("Vui lòng nhập lại");
                    }
                    if (etNgayDH.length() == 0) {
                        etNgayDH.setError("Vui lòng nhập lại");
                    }
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainDonDatHang.this);
                builder.setTitle("Notification!!!");
                builder.setMessage("You have delete ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_ddh.remove(index);
                        DonDatHang donDatHang = getDDH();
                      adapter_ddh.notifyDataSetChanged();
                        DBDonDatHang dbDonDatHang = new DBDonDatHang(getApplicationContext());
                        dbDonDatHang.xoa(donDatHang);

                        etMaDH.setText("");
                        etNgayDH.setText("");
                        spMaKH.getSelectedItem();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainDonDatHang.this);
                builder.setTitle("Notification!!");
                builder.setMessage("You have update?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DonDatHang donDatHang = data_ddh.get(index);
                        donDatHang.setMaDH(etMaDH.getText().toString());
                        donDatHang.setNgayDH(etNgayDH.getText().toString());
                        donDatHang.setmaKH(spMaKH.getSelectedItem().toString());
                        adapter_ddh.notifyDataSetChanged();
                        DBDonDatHang dbDonDatHang = new DBDonDatHang(getApplicationContext());
                        donDatHang = getDDH();
                        dbDonDatHang.sua(donDatHang);

                        etMaDH.setText("");
                        etNgayDH.setText("");
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
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainDonDatHang.this);
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

    private DonDatHang getDDH(){
        DonDatHang donDatHang = new DonDatHang();
        donDatHang.setMaDH(etMaDH.getText().toString());
        donDatHang.setNgayDH(etNgayDH.getText().toString());
        donDatHang.setmaKH(spMaKH.getSelectedItem().toString());
        return donDatHang;
    }
    private void ShowData() {
        DBDonDatHang dbDonDatHang = new DBDonDatHang(this);
        data_ddh = dbDonDatHang.getDataDDH();
        adapter_ddh = new CustomAdapter_DDH(this, R.layout.list_view_dondathang,data_ddh);
        lvDanhSachDDH.setAdapter(adapter_ddh);
    }

    private void LoadSpinner() {
        DBKhachHang dbKhachHang = new DBKhachHang(this);
        data_MaKH = dbKhachHang.LayMaKH();
        adapter_MaKH = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,data_MaKH);
        spMaKH.setAdapter(adapter_MaKH);
    }



    private void setControl() {
        etMaDH = findViewById(R.id.maDH);
        etNgayDH = findViewById(R.id.ngayDH);
        spMaKH = findViewById(R.id.spMaKH);

        btnThem = findViewById(R.id.Themdh);
        btnSua = findViewById(R.id.Suadh);
        btnXoa = findViewById(R.id.Xoadh);
        btnThoat = findViewById(R.id.Thoatdh);

        lvDanhSachDDH = findViewById(R.id.lvDSDDH);
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
                Toast.makeText(getApplicationContext(),"list customer",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),List_DDH.class);
                startActivity(intent);
                break;
            case R.id.thoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainDonDatHang.this);
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
