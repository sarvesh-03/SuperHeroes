package com.example.superheroes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    @GET("all.json")
    Call<List<superHero>>getAll();
}
