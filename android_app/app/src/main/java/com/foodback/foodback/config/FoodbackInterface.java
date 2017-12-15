package com.foodback.foodback.config;

import com.foodback.foodback.logic.Comment;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Foodback on 14/12/2017.
 */

public interface FoodbackInterface {

//    DELETE  /comments (com.lpro.fbrest.resources.CommentsResource)
//    POST    /comments (com.lpro.fbrest.resources.CommentsResource)
//    GET     /comments/{establishment_id} (com.lpro.fbrest.resources.CommentsResource)
//    GET     /credentials/establishment (com.lpro.fbrest.resources.CredentialsResource)
//    GET     /credentials/user (com.lpro.fbrest.resources.CredentialsResource)
//    GET     /establishments (com.lpro.fbrest.resources.EstablishmentsResource)
//    POST    /establishments (com.lpro.fbrest.resources.EstablishmentsResource)
//    PUT     /establishments (com.lpro.fbrest.resources.EstablishmentsResource)
//    GET     /establishments/{name} (com.lpro.fbrest.resources.EstablishmentsResource)
//    POST    /images/users (com.lpro.fbrest.resources.ImagesResource) -> comentado
//    GET     /images/{image} (com.lpro.fbrest.resources.ImagesResource) -> comentado
//    GET     /users (com.lpro.fbrest.resources.UsersResource)
//    POST    /users (com.lpro.fbrest.resources.UsersResource)
//    GET     /users/{username} (com.lpro.fbrest.resources.UsersResource)

    public static final String BASE_URL = "http://172.30.1.92:8080/";


    @DELETE("comments")
    Call deleteComment(@Field("comment_id") long comment_id);

    @POST("comments")
    Call<Long> newComment(@Field("comment") Comment comment);

    @GET("comments/{establishment_id}")
    Call<List<Comment>> getEstablishmentComments(@Path("establishment_id") long establishment_id);

    @GET("credentials/establishment")
    Call<ResponseBody> verifyEstablishmentCredentials();

    @GET("credentials/user")
    Call<ResponseBody> verifyUserCredentials();

    @GET("establishments")
    Call<List<Establishment>> getAllEstablishments();

    @POST("establishments")
    Call<ResponseBody> newEstablishment(@Field("establishment") Establishment establishment);

    @PUT("establishments")
    Call<ResponseBody> changeEstablishment(@Field("newestablishment") Establishment newestablishment);

    @GET("establishments/{name}")
    Call<Establishment> getEstablishment(@Path("name") String name);

//    @POST("images/users")
//    Call<Response> uploadUserImage(@Field("client") Client client, @FormDataParam("file") InputStream fileInputStream,
//                                   @FormDataParam("file") FormDataContentDisposition contentDispositionHeader);

    @GET("images/{image}")
    Call<ResponseBody> getImage(@Path("image") String image);


    @GET("users")
    Call<List<User>> getAllUsers(@Query("user") User user);

    @POST("users")
    Call<ResponseBody> newUser(@Field("user") User user);

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);


}
