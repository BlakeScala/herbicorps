package com.example.guest.herbicorpsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeMain extends AppCompatActivity {
    @Bind(R.id.recipeTitle) TextView mRecipeTitle;
    @Bind(R.id.recipeInstructions) TextView mRecipeInstructions;
    @Bind(R.id.)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String recipe = intent.getStringExtra("selectedRecipe");
        mRecipeTitle.setText(recipe);
    }
}
