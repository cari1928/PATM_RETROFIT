package me.apexjcl.retrofitsample.api;

import java.util.List;

import me.apexjcl.retrofitsample.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by radog on 04/05/2017.
 */

public interface PostAPI {

    @GET("/posts")
    Call<List<Post>> getPosts();
}
