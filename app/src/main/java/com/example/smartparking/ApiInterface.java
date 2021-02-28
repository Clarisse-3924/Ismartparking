package com.example.smartparking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("block")
    Call<List<ImageResponse>> getAllImages();
}
