package com.example.guest.herbicorpsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeSearch extends AppCompatActivity {
    @Bind(R.id.searchText) TextView mSearchText;
    @Bind(R.id.recipeListView) ListView mRecipeListView;

    public ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    public ArrayList<Recipe> savedRecipes = new ArrayList<Recipe>();

    Recipe recipe = new Recipe("Mac and Cashew Cheese", "Sample Instructions", "http://veganyumminess.com/wp-content/uploads/2014/04/Vegan-Mac-and-Cheese-2.jpg");
    Recipe recipeTwo = new Recipe("Macaroni and Cashew Cheese", "Here are instructions", "http://veganyumminess.com/wp-content/uploads/2014/04/Vegan-Mac-and-Cheese-2.jpg");
    Recipe recipeThree = new Recipe("Cashew Mac & Cheese", "How to make it", "http://veganyumminess.com/wp-content/uploads/2014/04/Vegan-Mac-and-Cheese-2.jpg");
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);
        ButterKnife.bind(this);
        recipes.add(recipe);
        recipes.add(recipeTwo);
        recipes.add(recipeThree);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_gallery_item, recipes);
        mRecipeListView.setAdapter(adapter);


        Intent intent = getIntent();
        String ingredientsInput = intent.getStringExtra("ingredients").toString();
        mSearchText.setText("Vegan meals including " + ingredientsInput);
        Toast.makeText(RecipeSearch.this, "API Call", Toast.LENGTH_LONG).show();

    }
}
