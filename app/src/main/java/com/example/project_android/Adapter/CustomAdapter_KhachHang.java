package com.example.project_android.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_android.Giaodien.Main_CTKH;
import com.example.project_android.Model.KhachHang;
import com.example.project_android.R;

import java.util.ArrayList;

public class CustomAdapter_KhachHang extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<KhachHang> data;
    ArrayList<KhachHang> data_DS;

    public CustomAdapter_KhachHang(@NonNull Context context, int resource, ArrayList<KhachHang> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<KhachHang>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private  static class Holder{
        TextView maKH, tenKH, diachi, sdt;
        ImageView imgDetail;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = null;

        if(view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource,null);
            holder.tenKH = view.findViewById(R.id.tenKH);
            holder.maKH = view.findViewById(R.id.soluong);
            holder.diachi = view.findViewById(R.id.diachi);
            holder.sdt = view.findViewById(R.id.sdt);
            holder.imgDetail = view.findViewById(R.id.image_CTKH);
            view.setTag(holder);
        }
        else
            holder = (Holder) view.getTag();

        final KhachHang khachHang = data.get(position);

        holder.maKH.setText(khachHang.getMaKH());
        holder.tenKH.setText(khachHang.getTenKH());
        holder.diachi.setText(khachHang.getDiachi());
        holder.sdt.setText(khachHang.getSdt());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, Main_CTKH.class);
                Bundle bundle = new Bundle();
                bundle.putString("maKH",khachHang.getMaKH());
                intent.putExtras(bundle);
                ((Activity)context).startActivity(intent);
            }
        });

        return view;
    }
}
