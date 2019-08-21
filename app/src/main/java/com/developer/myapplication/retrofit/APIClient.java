package com.developer.myapplication.retrofit;

public class APIClient {
    public static  final String URL="https://www.flickr.com";
    public static DataClient getData(){
        return RetrofitClient.getClient(URL).create(DataClient.class);
    }
}
