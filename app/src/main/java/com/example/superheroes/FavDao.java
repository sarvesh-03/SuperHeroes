package com.example.superheroes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface FavDao {
    @Query("SELECT * FROM favheroes")
    List<FavHero> getAllFav();

    @Query("SELECT * FROM favheroes WHERE id=:id")
    List<FavHero> getById(String id);

    @Delete
    void delete(FavHero favHero);
    @Insert
    void Insert(FavHero favHero);
}
