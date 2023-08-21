package com.example.salebookapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
//import com.example.salebookapp.AddCartActivity;
import com.example.salebookapp.AddCartActivity;
import com.example.salebookapp.R;
import com.example.salebookapp.fragment.CartFragment;
import com.example.salebookapp.model.Sach;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SachAdapter extends BaseAdapter {
    private List<Sach> listBook;
    private final Context context;
    private final LayoutInflater layoutInflater;
    private CartFragment cartFragment = new CartFragment();



    public SachAdapter(List<Sach> listBook, Context context) {
        this.listBook = listBook;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_listsach,parent,false);
        }



        ImageView imgTacGia = convertView.findViewById(R.id.img_sach);
        TextView tvName = convertView.findViewById(R.id.tv_tensach);
        TextView tvGia = convertView.findViewById(R.id.tv_giasach);
        TextView tvKhuyenMai = convertView.findViewById(R.id.tv_khuyenmai);
        Button btnBuy= convertView.findViewById(R.id.btnBuy);


        Float price = (listBook.get(position).getPrice());
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        numberFormat.format(price);
        Glide.with(context).load(listBook.get(position).getImg()).into(imgTacGia);
        tvName.setText(listBook.get(position).getTitle());
        tvGia.setText(String.format(Locale.getDefault(), "%.0f VNĐ", price));
        tvKhuyenMai.setText(listBook.get(position).getDiscount().getDescription());
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg ="Xác nhận đặt "+ tvName.getText() ;
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setTitle(msg)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Thêm "+tvName.getText() + " vào giỏ hàng thành công",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, AddCartActivity.class);
                                intent.putExtra("name", tvName.getText());
                                intent.putExtra("price",price);
                                intent.putExtra("img", listBook.get(position).getImg());
                                context.startActivity(intent);

                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Hủy",Toast.LENGTH_LONG).show();
                            }
                        })
                        .create()
                        .show();

            }
        });
        return convertView;
    }
}
