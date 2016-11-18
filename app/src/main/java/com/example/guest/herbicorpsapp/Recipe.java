package com.example.guest.herbicorpsapp;

/**
 * Created by Guest on 11/18/16.
 */
public class Recipe {
    private String recipeTitle;
    private String imageURL;
    private String instructions;

    public Recipe(String recipeTitle, String instructions, String imageURL) {
        this.recipeTitle = recipeTitle;
        this.instructions = instructions;
        this.imageURL = imageURL;
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
