package com.example.superheroes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApi {
    @GET("all.json")
    Call<List<superHero>>getAll();

    @GET("id/{id}.json")
    Call<superHero>getHero(@Path("id") String id);
}
