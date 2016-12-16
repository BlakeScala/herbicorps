package com.example.guest.herbicorpsapp.util;

import com.example.guest.herbicorpsapp.models.Recipe;

import java.util.ArrayList;

/**
 * Created by Guest on 12/16/16.
 */
public interface OnRecipeSelectedListener {
    public void onRecipeSelected(Integer position, ArrayList<Recipe> recipes);
}
