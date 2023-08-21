package com.example.salebookapp.api;

import com.example.salebookapp.model.Sach;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface SachAPI {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    SachAPI apiInterface = (SachAPI) new Retrofit.Builder()
            .baseUrl("https://book-api.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SachAPI.class);
    @GET("api/book/getAll")
    Call<List<Sach>> getList();
}
