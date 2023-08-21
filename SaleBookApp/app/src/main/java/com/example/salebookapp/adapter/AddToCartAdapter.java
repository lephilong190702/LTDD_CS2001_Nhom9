package com.example.salebookapp.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;


import com.bumptech.glide.Glide;
import com.example.salebookapp.R;
import com.example.salebookapp.databinding.ActivityDetailsBinding;

import com.example.salebookapp.model.Sach;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.HolderCart> {
    private Context context;
    private ArrayList<Sach> modelCartArrayList;

    public AddToCartAdapter(Context context, ArrayList<Sach> modelCartArrayList) {
        this.context = context;
        this.modelCartArrayList = modelCartArrayList;
    }

    private ActivityDetailsBinding binding;
    private static final String TAG ="Cart Sach";

    @NonNull
    @Override
    public HolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ActivityDetailsBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HolderCart(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCart holder, int position) {
        Sach sach = modelCartArrayList.get(position);
        loadBookDetail(sach,holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void loadBookDetail(Sach sach, HolderCart holder) {
        Integer bookId = sach.getId();
        Log.d(TAG,"loadBookDetail: Book Details of Book id"+bookId);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(String.valueOf(bookId))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = ""+snapshot.child("title").getValue();
                        Float price = (Float)snapshot.child("price").getValue();
                        String img = ""+snapshot.child("img").getValue();

                        sach.setTitle(name);
                        sach.setPrice(price);
                        sach.setImg(img);

                        holder.tvName.setText(name);
                        holder.tvGia.setText(String.valueOf(price));
                        Glide.with(context).load(img).into(holder.img);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return modelCartArrayList.size();
    }

    class HolderCart extends RecyclerView.ViewHolder{
        TextView tvName, tvGia;
        ImageView img;

        public HolderCart(@NonNull View itemView) {
            super(itemView);
            tvName= binding.detailsFragment.findViewById(R.id.tv_tensachc);
            tvGia=binding.detailsFragment.findViewById(R.id.tv_giasachc);
            img=binding.detailsFragment.findViewById(R.id.img_sachc);
        }
    }
}
