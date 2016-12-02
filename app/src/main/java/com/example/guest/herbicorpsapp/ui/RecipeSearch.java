package com.example.guest.herbicorpsapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.herbicorpsapp.R;
import com.example.guest.herbicorpsapp.models.Recipe;
import com.example.guest.herbicorpsapp.services.YummlyService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeSearch extends AppCompatActivity {
    @Bind(R.id.searchText) TextView mSearchText;
    @Bind(R.id.recipeListView) ListView mRecipeListView;

    public ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String foodSearchInput = intent.getStringExtra("ingredients");
        mSearchText.setText("Vegan meals including " + foodSearchInput);

        getRecipes(foodSearchInput);

    }

    private void getRecipes(String foodInput) {
        final YummlyService yummlyService = new YummlyService();
        yummlyService.findRecipes(foodInput, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRecipes = yummlyService.processResults(response);

                RecipeSearch.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] recipeNames = new String[mRecipes.size()];
                        for (int i = 0; i < recipeNames.length; i ++) {
                            recipeNames[i] = mRecipes.get(i).getRecipeName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(RecipeSearch.this, android.R.layout.simple_list_item_1, recipeNames);
                        mRecipeListView.setAdapter(adapter);

                        for (String recipeName : recipeNames) {
                            Log.d("Attributes", "name: " + recipeName);
                        }
                    }
                });
            }
        });
    }
}
