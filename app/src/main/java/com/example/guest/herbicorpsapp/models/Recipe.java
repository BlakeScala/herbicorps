package com.example.guest.herbicorpsapp.models;

import android.util.Log;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Recipe {
    String recipeName;
    String sourceName;
    String imageURL;
    List<String> ingredients = new ArrayList<>();
    int estimatedTime;
    int rating;
    private String pushId;
    String index;

    public Recipe() {

    }

    public Recipe(String recipeName, String sourceName, String imageURL, ArrayList<String> ingredients, int estimatedTime, int rating, String id) {
        this.recipeName = recipeName;
        this.sourceName = sourceName;
        this.imageURL = imageURL;
        this.ingredients = ingredients;
        this.estimatedTime = estimatedTime;
        this.rating = rating;
        this.index = "not_specified";
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getImageURL() {
        String largeImageURL = imageURL.substring(0, imageURL.length()-4).concat("600-c");
        Log.v("log", imageURL);
        Log.v("log", largeImageURL);
        return largeImageURL;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public int getRating() {
        return rating;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}