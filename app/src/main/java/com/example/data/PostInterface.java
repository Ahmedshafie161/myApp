package com.example.data;

import com.example.pojo.PostModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostInterface {

    //recieve data from server,  other than base url
    @GET("posts/1")
    public Call<PostModel> getPost();
    @GET ("posts/{id)")
    public Call<PostModel> getPostID (@Path("id") int postId);

    //query posts
    @GET("posts")
    public Call<List<PostModel>> getPosts(@Query("userId") String userId) ;



    /* @GET("posts")
   public Callpo<List<PostModel>> getPosts() ;*/
    @GET("posts")
    //change return type from call to observable
    public Observable<List<PostModel>> getPosts() ;


    //send data to server
    @POST("post")
    public Call<PostModel> storePost (@Body PostModel postModel);

    //send map of objects
    @POST("posts")
    public Call<PostModel> storePost (@Body HashMap<Object, Object>map);



}
