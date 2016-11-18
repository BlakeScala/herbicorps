package com.example.guest.herbicorpsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeMain extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.recipeTitle) TextView mRecipeTitle;
    @Bind(R.id.recipeInstructions) TextView mRecipeInstructions;
    @Bind(R.id.xButton) Button mXButton;
    @Bind(R.id.checkButton) Button mCheckButton;

    public ArrayList<String> checkedRecipes = new ArrayList<String>();
    public String recipeToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String recipe = intent.getStringExtra("selectedRecipe");
        recipeToAdd = recipe;
        mRecipeTitle.setText(recipe);
    }

    @Override
    public void onClick(View v) {
        if(v == mXButton) {
            Intent intent = new Intent(RecipeMain.this, RecipeSearch.class);
        } else if(v == mCheckButton) {
            checkedRecipes.add(recipeToAdd);
            Toast.makeText(RecipeMain.this, "Added to Favorites", Toast.LENGTH_LONG).show();
        }
    }
}
