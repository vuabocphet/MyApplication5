package com.developer.myapplication.retrofit;


import com.developer.myapplication.HinhNen;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;


public interface DataClient {

      //Code GET ALL DATA
    @GET("/services/rest/?method=flickr.favorites.getList&api_key=24bf810575bc5bfbe2aef1ed6cd4517b&user_id=63356846%40N04&format=json&nojsoncallback=1&extras=views,%20media,%20path_alias,%20url_sq,%20url_t,%20url_s,%20url_q,%20url_m,%20url_n,%20url_z,%20url_c,%20url_l,%20url_o&per_page=10&page=1")
    Call<HinhNen> getDataALL();







}
