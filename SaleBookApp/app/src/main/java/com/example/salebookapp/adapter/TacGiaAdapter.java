package com.example.salebookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salebookapp.R;
import com.example.salebookapp.model.Sach;
import com.example.salebookapp.model.TacGia;


import java.util.List;

public class TacGiaAdapter extends RecyclerView.Adapter<TacGiaAdapter.TacGiaViewHolder> {
    List<Sach> listTacGia;
    Context context;

    public TacGiaAdapter(List<Sach> listTacGia, Context context) {
        this.listTacGia = listTacGia;
        this.context = context;
    }

    @NonNull
    @Override
    public TacGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listtacgia, parent, false);
        return new TacGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TacGiaViewHolder holder, int position) {
        holder.tvName.setText(listTacGia.get(position).getAuthor().getName());
        Glide.with(context).load(listTacGia.get(position).getAuthor().getImg()).into(holder.imgUser);
    }

    @Override
    public int getItemCount() {
        return listTacGia.size();
    }

    public static class TacGiaViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgUser;
        public TextView tvName;
        public TacGiaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

}

