package com.example.salebookapp.fragment;



import android.content.Context;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.salebookapp.AddCartActivity;
import com.example.salebookapp.R;

import com.example.salebookapp.adapter.AddToCartAdapter;
import com.example.salebookapp.adapter.CartAdapter;

import com.example.salebookapp.api.SachAPI;
import com.example.salebookapp.model.Sach;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Formattable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Sach> listSach;
    String name, img;
    Float price;
    CartAdapter adapter;
    GridView gvCart;
    ImageView imgAnh;
    TextView tvTen,tvGia;

    Context context;



    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context= CartFragment.this.getContext();
        Intent intent = new Intent(context,AddCartActivity.class);
        startActivity(intent);
        View view=  inflater.inflate(R.layout.fragment_cart, container, false);
//        gvCart= view.findViewById(R.id.gv_cart);
//        name = getActivity().getIntent().getStringExtra("name");
//        img=getActivity().getIntent().getStringExtra("img");
//        price=getActivity().getIntent().getFloatExtra("price",0);
//        imgAnh = view.findViewById(R.id.img_sachc);
//        tvGia= view.findViewById(R.id.tv_giasachc);
//        tvTen= view.findViewById(R.id.tv_tensachc);
//
//        Glide.with(this.getContext()).load(img).into(imgAnh);
//        tvTen.setText(name);
//        tvGia.setText(String.valueOf(price));


        return view;
    }

}


