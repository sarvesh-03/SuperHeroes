package com.example.superheroes;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {FavHero.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FavDao favDao();
}
