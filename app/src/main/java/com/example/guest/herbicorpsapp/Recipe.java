package com.example.guest.herbicorpsapp;

/**
 * Created by Guest on 11/18/16.
 */
public class Recipe {
    private String recipeTitle;
    private String imageURL;
    private String instructions;

    public Recipe(String recipeTitle, String imageURL, String instructions) {
        this.recipeTitle = recipeTitle;
        this.imageURL = imageURL;
        this.instructions = instructions;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getInstructions() {
        return instructions;
    }
}
