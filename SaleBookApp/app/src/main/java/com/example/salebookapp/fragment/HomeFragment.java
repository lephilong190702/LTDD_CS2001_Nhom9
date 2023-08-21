package com.example.salebookapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.salebookapp.ExpandableHeightGridView;
import com.example.salebookapp.R;
import com.example.salebookapp.SearchActivity;
import com.example.salebookapp.adapter.SachAdapter;
import com.example.salebookapp.adapter.TacGiaAdapter;
import com.example.salebookapp.api.SachAPI;
import com.example.salebookapp.model.Sach;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ViewFlipper bannerAd;
    RecyclerView rcvTacGia;
    List<Sach> list;
    ExpandableHeightGridView gvPopularBook;
    TextView tvSearch;
    SachAdapter bookAdapter;
    TacGiaAdapter tacGiaAdapter;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bannerAd = view.findViewById(R.id.vfp_bannerad);
        ActionViewFlipper();

        rcvTacGia = view.findViewById(R.id.rcvTacGia);
        rcvTacGia.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        gvPopularBook = (ExpandableHeightGridView) view.findViewById(R.id.gv_sachnoibat);
        gvPopularBook.setExpanded(true);

        tvSearch = view.findViewById(R.id.tv_timkiem);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, SearchActivity.class);
                startActivity(intent);
            }
        });


        getListTacGia();
        getListPopularBook();
        context=HomeFragment.this.getContext();
        return view;
    }

    private void ActionViewFlipper() {
        List<String> quangCao = new ArrayList<>();
        quangCao.add("https://bookbuy.vn/Res/Images/Album/ffd62e0e-02fb-4e7a-96fe-42ed8966c89b.png?w=920&h=420&mode=crop");
        quangCao.add("https://suckhoedoisong.qltns.mediacdn.vn/Images/duylinh/2021/05/31/sach%20onlie.jpg");
        quangCao.add("https://bookbuy.vn/Res/Images/Album/00456729-e290-44a5-bbe9-da9a261ff77b.png?w=920&h=420&mode=crop");
        quangCao.add("https://indec.vn/wp-content/uploads/2021/01/son_280121_PNG_WEB_tamlyhoc.png-1.png");
        quangCao.add("https://newshop.vn/public/uploads/news/69.jpg");

        for (int i = 0; i < quangCao.size(); i++) {
            ImageView imageView = new ImageView(this.getContext());
            Glide.with(this.requireContext()).load(quangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bannerAd.addView(imageView);
        }
        bannerAd.setFlipInterval(3000);
        bannerAd.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_right);
        bannerAd.setInAnimation(slide_in);
        bannerAd.setOutAnimation(slide_out);
    }

    public void getListTacGia() {
        SachAPI.apiInterface.getList().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list.addAll(response.body());
                tacGiaAdapter = new TacGiaAdapter(list, context);
                rcvTacGia.setAdapter(tacGiaAdapter);
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getListPopularBook() {
        SachAPI.apiInterface.getList().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list.addAll(response.body());
                bookAdapter = new SachAdapter(list, context);
                gvPopularBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

