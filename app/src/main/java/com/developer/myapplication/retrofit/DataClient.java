package com.developer.myapplication.retrofit;


import com.developer.myapplication.HinhNen;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;


public interface DataClient {

      //Code GET ALL DATA
    @GET("/services/rest/")
    Call<List<HinhNen>> getDataALL(@Query("method") String method, @Query("api_key") String api_key, @Query("user_id") String user_id, @Query("format") String format, @Query("nojsoncallback")String nojsoncallback, @Query("extras")String extras, @Query("per_page")String per_page, @Query("page")String page);







}
