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

import com.example.project_android.Giaodien.Main_CTSP;
import com.example.project_android.Giaodien.Main_CTTTDDH;
import com.example.project_android.Model.SanPham;
import com.example.project_android.R;

import java.util.ArrayList;

public class CustomAdapter_SanPham extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<SanPham> data;
    ArrayList<SanPham> data_sp;

    public CustomAdapter_SanPham(@NonNull Context context, int resource, ArrayList<SanPham> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_sp = new ArrayList<SanPham>();
        this.data_sp.addAll(data);
    }

    private static  class Holder{
        TextView spMaSP, tenSP, xuatxu, dongia;
        ImageView imgDetail;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        Holder holder = null;
        if(view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource,null);
            holder.spMaSP = view.findViewById(R.id.spMaSP);
            holder.tenSP = view.findViewById(R.id.tenSP);
            holder.xuatxu = view.findViewById(R.id.xuatxu);
            holder.dongia = view.findViewById(R.id.dongia);
            holder.imgDetail = view.findViewById(R.id.imageRead);
            view.setTag(holder);
        }
        else
        {
            holder = (Holder)view.getTag();
            final  SanPham sanPham = data.get(position);

            holder.spMaSP.setText(sanPham.getMaSP());
            holder.tenSP.setText(sanPham.getTenSP());
            holder.xuatxu.setText(sanPham.getXuatxu());
            holder.dongia.setText(sanPham.getdongia());

            holder.imgDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Activity) context, Main_CTSP.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("maSP",sanPham.getMaSP());
                    intent.putExtras(bundle);
                    ((Activity) context).startActivity(intent);
                }
            });

        }
        return view;
    }
}
