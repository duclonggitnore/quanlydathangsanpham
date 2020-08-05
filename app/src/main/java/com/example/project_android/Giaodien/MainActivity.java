package com.example.project_android.Giaodien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.project_android.R;

public class MainActivity extends AppCompatActivity {
    ImageButton btnKH, btnDDH, btnThongTinDH, btnSP;
    Button btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
                loadingDialog.startLoad();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 1000);
                Intent intent = new Intent(MainActivity.this,MainKhachHang.class);
                startActivity(intent);
            }
        });

        btnDDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
                loadingDialog.startLoad();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 1000);
                Intent intent = new Intent(MainActivity.this, MainDonDatHang.class);
                startActivity(intent);
            }
        });

        btnThongTinDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainThongTinDatHang.class);
                final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
                loadingDialog.startLoad();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 1000);
                startActivity(intent);
            }
        });

        btnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainSanPham.class);
                final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
                loadingDialog.startLoad();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 1000);
                startActivity(intent);
            }
        });

//        btnThoat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Main)
//            }
//        });
    }

    private void setControl() {
        btnKH = findViewById(R.id.imageKH);
        btnDDH = findViewById(R.id.imageTTDDH);
        btnThongTinDH = findViewById(R.id.imageThongTinDH);
        btnSP = findViewById(R.id.imageSP);
        btnThoat = findViewById(R.id.btnThoatMain);
    }

}
