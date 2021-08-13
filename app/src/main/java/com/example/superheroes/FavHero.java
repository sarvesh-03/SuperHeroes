package com.example.superheroes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favheroes",indices = {@Index(value = {"id","name"},unique = true)})
public class FavHero {
    @NonNull
    @PrimaryKey
    private String id;

    public String getName() {
        return name;
    }

    private String name;

    public FavHero(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
