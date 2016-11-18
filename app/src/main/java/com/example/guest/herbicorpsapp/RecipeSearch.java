package com.example.guest.herbicorpsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

//    public ArrayList<Recipe> recipes = new ArrayList<Recipe>();
//    public ArrayList<Recipe> savedRecipes = new ArrayList<Recipe>();

    public ArrayList<String> recipes = new ArrayList<String>();
    public ArrayList<String> savedRecipes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);
        ButterKnife.bind(this);
        recipes.add("Mac and Cashew Cheese");
        recipes.add("Macaroni and Cashew Cheese");
        recipes.add("Cashew Cheese Macaroni");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes);
        mRecipeListView.setAdapter(adapter);

        mRecipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(RecipeSearch.this, RecipeMain.class);
                intent.putExtra("selectedRecipe", clickedItem);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        String ingredientsInput = intent.getStringExtra("ingredients").toString();
        mSearchText.setText("Vegan meals including " + ingredientsInput);
        Toast.makeText(RecipeSearch.this, "API Call", Toast.LENGTH_LONG).show();

    }
}
