package com.foodback.foodback.config;

import com.foodback.foodback.logic.Client;
import com.foodback.foodback.logic.Comment;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Foodback on 14/12/2017.
 */

public interface RetrofitInterface {

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

    @FormUrlEncoded
    @DELETE("/comments")
    Call deleteComment(@Field("comment_id") long comment_id);

    @FormUrlEncoded
    @POST("/comments")
    Call<Long> newComment(@Field("client") Client client, @Field("comment") Comment comment);

    @FormUrlEncoded
    @GET("/comments/{establishment_id}")
    Call<List<Comment>> getEstablishmentComments(@Path("establishment_id") long establishment_id);

    @FormUrlEncoded
    @GET("/credentials/establishment")
    Call<Response> verifyEstablishmentCredentials(@Query("client") Client client);

    @FormUrlEncoded
    @GET("/credentials/user")
    Call<Response> verifyUserCredentials(@Query("client") Client client);

    @FormUrlEncoded
    @GET("/establishments")
    Call<List<Establishment>> getAllEstablishments();

    @FormUrlEncoded
    @POST("/establishments")
    Call<Response> newEstablishment(@Field("establishment") Establishment establishment);

    @FormUrlEncoded
    @PUT("/establishments")
    Call<Response> changeEstablishment(@Field("client") Client client, @Field("newestablishment") Establishment newestablishment);

    @FormUrlEncoded
    @GET("/establishments/{name}")
    Call<Establishment> getEstablishment(@Path("name") String name);

//    @FormUrlEncoded
//    @POST("/images/users")
//    Call<Response> uploadUserImage(@Field("client") Client client, @FormDataParam("file") InputStream fileInputStream,
//                                   @FormDataParam("file") FormDataContentDisposition contentDispositionHeader);

    @FormUrlEncoded
    @GET("/images/{image}")
    Call<Response> getImage(@Path("image") String image);


    @FormUrlEncoded
    @GET("/users")
    Call<List<User>> getAllUsers(@Query("user") User user);

    @FormUrlEncoded
    @POST("/users")
    Call<Response> newUser(@Field("user") User user);

    @FormUrlEncoded
    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);


}
