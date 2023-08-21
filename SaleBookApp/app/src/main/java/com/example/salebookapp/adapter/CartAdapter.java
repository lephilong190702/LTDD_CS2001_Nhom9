package com.example.salebookapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.salebookapp.AddCartActivity;
import com.example.salebookapp.R;
import com.example.salebookapp.model.Sach;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends BaseAdapter {
    private List<Sach> listBook;
    private final Context context;
    private final LayoutInflater layoutInflater;


    public CartAdapter(List<Sach> listBook, Context context) {
        this.listBook = listBook;
        this.context = context;
        this.layoutInflater = (LayoutInflater.from(context));
    }


    public void filterList(List<Sach> filterList){
        listBook = filterList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return listBook.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.cartlist,parent,false);
        }



        ImageView imgTacGia = convertView.findViewById(R.id.img_sachc);
        TextView tvName = convertView.findViewById(R.id.tv_tensachc);
        TextView tvGia = convertView.findViewById(R.id.tv_giasachc);
        ImageView imgDelete = convertView.findViewById(R.id.img_delete);




        Glide.with(context).load(listBook.get(i).getImg()).into(imgTacGia);
        Float price = (listBook.get(i).getPrice());
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        numberFormat.format(price);
        tvName.setText(listBook.get(i).getTitle());
        tvGia.setText(String.format(Locale.getDefault(), "%.0f VNĐ", price));
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg ="Bạn muốn xóa "+ tvName.getText() +" khỏi giỏ hàng";
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setTitle(msg)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Đã xóa "+tvName.getText() +" khỏi giỏ hàng",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(context, AddCartActivity.class);
                                intent.putExtra("name2", tvName.getText());
                                intent.putExtra("price2",price);
                                context.startActivity(intent);

                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create()
                        .show();

            }
        });
        return convertView;
    }
}
