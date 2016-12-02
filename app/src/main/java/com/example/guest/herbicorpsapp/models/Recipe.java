package com.example.guest.herbicorpsapp.models;

import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private String sourceName;
    private String imageURL;
    private ArrayList<String> ingredients;
    private int estimatedTime;
    private int rating;
    private String id;

    public Recipe(String recipeName, String sourceName, String imageURL, ArrayList<String> ingredients, int estimatedTime, int rating, String id) {
        this.recipeName = recipeName;
        this.sourceName = sourceName;
        this.imageURL = imageURL;
        this.ingredients = ingredients;
        this.estimatedTime = estimatedTime;
        this.rating = rating;
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public int getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }


}