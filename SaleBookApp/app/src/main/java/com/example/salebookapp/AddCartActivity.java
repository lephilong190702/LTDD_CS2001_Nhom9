package com.example.salebookapp;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.salebookapp.adapter.CartAdapter;
import com.example.salebookapp.model.Sach;

import java.util.ArrayList;
import java.util.List;


public class AddCartActivity extends AppCompatActivity {

    ImageView imgAnh;
    TextView tvTen,tvGia,tvTotal;

    String name,name2, img;
    Float price, price2;
    static double total =0;
    CartAdapter adapter;
    static List<Sach> list = new ArrayList<>();
    GridView gvCart;
    Button btnBack, btnTotal;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);


        gvCart = findViewById(R.id.gv_cart);
        btnBack = findViewById(R.id.btnBack);
        tvTotal= findViewById(R.id.tv_total);
        btnTotal= findViewById(R.id.btnTotal);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        name = getIntent().getStringExtra("name");
        img=getIntent().getStringExtra("img");
        price=getIntent().getFloatExtra("price",0);
        total += price;
        if(name!= null){
            list.add(new Sach(name,img,price));
            adapter = new CartAdapter(list,AddCartActivity.this);
        }
        name2 = getIntent().getStringExtra("name2");
        price2=getIntent().getFloatExtra("price2",0);
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getTitle().equals(name2)) {
                list.remove(i);
                total -= price2;
            }
            adapter = new CartAdapter(list,AddCartActivity.this);
        }

        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.removeAll(list);
                Toast.makeText(AddCartActivity.this,"Đã thanh toán", Toast.LENGTH_SHORT).show();
                total =0;

                Intent intent = new Intent(AddCartActivity.this,AddCartActivity.class);
                startActivity(intent);
            }
        });
        tvTotal.setText("Total:"+total+"VND");
        adapter = new CartAdapter(list,AddCartActivity.this);
        gvCart.setAdapter(adapter);

//        imgAnh = findViewById(R.id.img_sachc);
//        tvGia= findViewById(R.id.tv_giasachc);
//        tvTen= findViewById(R.id.tv_tensachc);
//
//        Glide.with(AddCartActivity.this.getApplicationContext()).load(img).into(imgAnh);
//        tvTen.setText(name);
//        tvGia.setText(String.valueOf(price));


    }
}