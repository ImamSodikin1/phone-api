package com.example.api.service;

import com.example.api.model.Phone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PhoneApiService {
    @GET("/api/{id}")
    Call<Phone> getPhoneById(@Path("id") int id);
    @POST("/api/")
    Call<Phone> createPhone(@Body Phone phone);

    @PUT("/api/{id}")
    Call<Phone> updatePhone(@Path("id") int id, @Body Phone phone);

    @DELETE("/api/{id}")
    Call<Void> deletePhone(@Path("id") int id);
}

