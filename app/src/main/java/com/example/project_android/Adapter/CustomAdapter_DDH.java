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

import com.example.project_android.Giaodien.Main_CTDDH;
import com.example.project_android.Model.DonDatHang;
import com.example.project_android.R;

import java.util.ArrayList;

public class CustomAdapter_DDH extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DonDatHang> data;
    ArrayList<DonDatHang> data_DDH;

    public CustomAdapter_DDH(@NonNull Context context, int resource, ArrayList<DonDatHang> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DDH = new ArrayList<DonDatHang>();
        this.data_DDH.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private  static  class Holder{
        TextView maDH, ngayDH, maKH;
        ImageView imgDetail;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view  = convertView;
        Holder holder = null;

        if(view == null)
        {
            holder = new Holder();
            view  = LayoutInflater.from(context).inflate(resource,null);
            holder.maDH = view.findViewById(R.id.maDH);
            holder.ngayDH = view.findViewById(R.id.ngayDH);
            holder.maKH = view.findViewById(R.id.spMaKH);
            holder.imgDetail = view.findViewById(R.id.imageRead);
            view.setTag(holder);
        }
        else
            holder = (Holder) view.getTag();
        final  DonDatHang donDatHang = data.get(position);

        holder.maDH.setText(donDatHang.getMaDH());
        holder.ngayDH.setText(donDatHang.getNgayDH());
        holder.maKH.setText(donDatHang.getmaKH());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, Main_CTDDH.class);
                Bundle bundle = new Bundle();
                bundle.putString("maDH",donDatHang.getMaDH());
                intent.putExtras(bundle);
                ((Activity)context).startActivity(intent);
            }
        });
        return view;
    }
}
