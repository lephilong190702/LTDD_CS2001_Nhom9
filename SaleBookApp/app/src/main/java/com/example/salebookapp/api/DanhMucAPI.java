package com.example.salebookapp.api;

import com.example.salebookapp.model.Sach;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DanhMucAPI {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    DanhMucAPI apiInterface = new Retrofit.Builder()
            .baseUrl("https://book-api.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DanhMucAPI.class);
    @GET("api/book/getByCategory/3222")
    Call<List<Sach>> getThieuNhi();

    @GET("api/book/getByCategory/3333")
    Call<List<Sach>> getKinhDoanh();

    @GET("api/book/getByCategory/3444")
    Call<List<Sach>> getKhoaHoc();

    @GET("api/book/getByCategory/3555")
    Call<List<Sach>> getVanHoa();

    @GET("api/book/getByCategory/3666")
    Call<List<Sach>> getLichSu();

    @GET("api/book/getByCategory/3777")
    Call<List<Sach>> getGiaoDuc();
}
