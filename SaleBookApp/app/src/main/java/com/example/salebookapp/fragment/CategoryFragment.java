package com.example.salebookapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salebookapp.R;
import com.example.salebookapp.SearchActivity;
import com.example.salebookapp.adapter.SachAdapter;
import com.example.salebookapp.api.DanhMucAPI;
import com.example.salebookapp.api.SachAPI;
import com.example.salebookapp.model.Sach;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Sach> list;
    GridView gvBook;
    LinearLayout lnrThieuNhi, lnKhoaHoc, lnrVanHoa, lnrKinhDoanh, lnrGiaoDuc, lnrLichSu;

    SachAdapter bookAdapter;
    TextView tvSearch;
    Context context;


    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        context = CategoryFragment.this.getContext();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        gvBook = view.findViewById(R.id.gv_sach);
        Toast.makeText(context, "Giáo dục", Toast.LENGTH_SHORT).show();
        getGiaoDuc();

        tvSearch = view.findViewById(R.id.tv_timkiem);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, SearchActivity.class);
                startActivity(intent);
            }
        });

        lnrGiaoDuc = view.findViewById(R.id.lnr_giaoduc);
        lnrGiaoDuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGiaoDuc();
                Toast.makeText(context, "Giáo dục", Toast.LENGTH_SHORT).show();
            }
        });

        lnrThieuNhi = view.findViewById(R.id.lnr_thieunhi);
        lnrThieuNhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getThieuNhi();
                Toast.makeText(context, "Thiếu nhi", Toast.LENGTH_SHORT).show();
            }
        });

        lnrKinhDoanh =view.findViewById(R.id.lnr_kinhdoanh);
        lnrKinhDoanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Kinh doanh", Toast.LENGTH_SHORT).show();
                getKinhDoanh();
            }
        });

        lnKhoaHoc = view.findViewById(R.id.lnr_khoahoc);
        lnKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Khoa học - công nghệ", Toast.LENGTH_SHORT).show();
                getKhoaHoc();
            }
        });

        lnrVanHoa = view.findViewById(R.id.lnr_vanhoa);
        lnrVanHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Văn hóa", Toast.LENGTH_SHORT).show();
                getVanHoa();
            }
        });

        lnrLichSu = view.findViewById(R.id.lnr_lichsu);
        lnrLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Lịch sử", Toast.LENGTH_SHORT).show();
                getLichSu();
            }
        });

       return view;
    }



    public void getGiaoDuc() {
        DanhMucAPI.apiInterface.getGiaoDuc().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list.addAll(response.body());
                bookAdapter = new SachAdapter(list, context);
                gvBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getThieuNhi() {
        DanhMucAPI.apiInterface.getThieuNhi().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list = response.body();
                bookAdapter = new SachAdapter(list, context);
                gvBook.setAdapter(bookAdapter);
            }
            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getKinhDoanh() {
        DanhMucAPI.apiInterface.getKinhDoanh().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list = response.body();
                bookAdapter = new SachAdapter(list, context);
                gvBook.setAdapter(bookAdapter);
            }
            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getKhoaHoc() {
        DanhMucAPI.apiInterface.getKhoaHoc().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list = response.body();
                bookAdapter = new SachAdapter(list, context);
                gvBook.setAdapter(bookAdapter);
            }
            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getVanHoa() {
        DanhMucAPI.apiInterface.getVanHoa().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list = response.body();
                bookAdapter = new SachAdapter(list, context);
                gvBook.setAdapter(bookAdapter);
            }
            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLichSu() {
        DanhMucAPI.apiInterface.getLichSu().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                list = new ArrayList<>();
                list = response.body();
                bookAdapter = new SachAdapter(list,context);
                gvBook.setAdapter(bookAdapter);
            }
            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}