package com.foodback.foodback.config;

import com.foodback.foodback.logic.Comment;
import com.foodback.foodback.logic.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by FoodBack.
 */

public interface CommentEndpoints {

//    GET     /comments/{comment_id}/commenter (com.lpro.fbrest.resources.CommentsResource)  -check
//    DELETE     /comments/{comment_id} (com.lpro.fbrest.resources.CommentsResource)  -check
//    GET     /comments/id/{comment_id} (com.lpro.fbrest.resources.CommentsResource)  -check
//    GET     /comments/{establishment_id} (com.lpro.fbrest.resources.CommentsResource)  -check
//    POST    /comments (com.lpro.fbrest.resources.CommentsResource)


    @GET("/comments/{comment_id}/commenter")
    Call<User> getCommenter(@Path("comment_id") long comment_id);

    @DELETE("/comments/{comment_id}")
    Call<ResponseBody> deleteComment(@Path("comment_id") long comment_id);

    @GET("/comments/id/{comment_id}")
    Call<Comment> getComment(@Path("comment_id") long comment_id);

    @GET("/comments/{establishment_id}")
    Call<List<Comment>> getEstablishmentComments(@Path("establishment_id") long establishment_id);

}
