package com.example.guest.herbicorpsapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.herbicorpsapp.R;

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

        mXButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mXButton) {
            Toast.makeText(RecipeMain.this, "Delete + Navigate back using intent with request code", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(RecipeMain.this, RecipeListActivity.class);
//            startActivityForResult(intent, 1);
        } else if(v == mCheckButton) {
            checkedRecipes.add(recipeToAdd);
            Toast.makeText(RecipeMain.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
        }
    }
}
