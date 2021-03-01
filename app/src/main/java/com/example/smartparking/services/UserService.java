package com.example.smartparking.services;

import com.example.smartparking.models.LoginRequest;
import com.example.smartparking.models.LoginResponse;
import com.example.smartparking.models.RegisterRequest;
import com.example.smartparking.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("token/")
    Call<LoginResponse> LoginUser(@Body LoginRequest loginRequest);

    @POST("api/register/")
    Call<RegisterResponse> RegisterUser(@Body RegisterRequest registerRequest);

}
