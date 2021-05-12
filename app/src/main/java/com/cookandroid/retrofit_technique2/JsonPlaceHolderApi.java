package com.cookandroid.retrofit_technique2;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("/users/{user}")
    Call<Post> getPost(@Path("user") String path);

    @FormUrlEncoded
    @POST("/users/yechan2491")
    Call<Post> postData(@FieldMap HashMap<String , Object> param);
}
