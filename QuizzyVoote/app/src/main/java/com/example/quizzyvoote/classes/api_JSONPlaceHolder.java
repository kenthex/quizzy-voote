package com.example.quizzyvoote.classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface api_JSONPlaceHolder {

    /// USERS

    @GET("/users")
    public Call<List<api_Users>> getUsers();

    @GET("/users/name/{name}")
    public Call<api_Users> getUserByName(@Path("name") String name);

    @POST("/users/add")
    public Call<api_Users> createUser(@Body api_Users data);

    @POST("/users/login")
    public Call<api_Tokens> authUser(@Body api_Tokens data);

    /// TOKENS

    @GET("/tokens/{token}")
    public Call<api_Tokens> getToken(@Path("token") String token);

    @DELETE("/tokens/{token}")
    public Call<api_Tokens> delToken(@Path("token") String token);



}

