package com.cschua.myapplication.repository.remote;

import com.cschua.myapplication.repository.remote.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteAPI {

    @GET("users")
    Call<UserResponse> getUsers(@Query("page") long page);

    @GET("rest/")
    public Image getImageList(
        @Query("api_key") String apiKey,
        @Query("per_page") int itemCount,
        @Query("text") String term,
        @Query("method") String method,// "flickr.photos.search",
        @Query("privacy_filter") int privacy,//: Int = 1,
        @Query("format") String format,//: String = "json",
        @Query("nojsoncallback") int callback,//: Int = 1,
        @Query("page") int page//: Int
    );
}
