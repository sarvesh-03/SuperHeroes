package com.example.superheroes;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class superHero implements Serializable {
    private String id;
    private String name;
    private String slug;
    private JsonObject powerstats;
    private JsonObject appearance;
    private JsonObject biography;
    private JsonObject work;
    private JsonObject connections;
    private JsonObject images;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public JsonObject getPowerstats() {
        return powerstats;
    }

    public JsonObject getAppearance() {
        return appearance;
    }

    public JsonObject getBiography() {
        return biography;
    }

    public JsonObject getWork() {
        return work;
    }

    public JsonObject getConnections() {
        return connections;
    }

    public JsonObject getImages() {
        return images;
    }
}
