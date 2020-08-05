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

import com.example.project_android.Giaodien.Main_CTTTDDH;
import com.example.project_android.Model.ThongTinDDH;
import com.example.project_android.R;

import java.util.ArrayList;

public class CustomAdapter_TTDDH extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ThongTinDDH> data;
    ArrayList<ThongTinDDH> data_ttdh;

    public CustomAdapter_TTDDH(@NonNull Context context, int resource, ArrayList<ThongTinDDH> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_ttdh = new ArrayList<ThongTinDDH>();
        this.data_ttdh.addAll(data);
    }

    private static  class Holder{
        TextView maDH, maSP, soluong;
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
            holder.maDH = view.findViewById(R.id.spMaDH);
            holder.maSP = view.findViewById(R.id.maSP);
            holder.soluong = view.findViewById(R.id.soluong);
            holder.imgDetail = view.findViewById(R.id.imageRead);
            view.setTag(holder);
        }
        else{
            holder = (Holder)view.getTag();
            final ThongTinDDH thongTinDDH = data.get(position);
            
            holder.maDH.setText(thongTinDDH.getmaDH());
            holder.maSP.setText(thongTinDDH.getMaSP());
            holder.soluong.setText(thongTinDDH.getSoluong());

            holder.imgDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Activity) context, Main_CTTTDDH.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("maDH",thongTinDDH.getmaDH());
                    intent.putExtras(bundle);
                    ((Activity) context).startActivity(intent);
                }
            });
        }
        return view;
    }
}
