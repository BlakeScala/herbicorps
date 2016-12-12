package com.example.guest.herbicorpsapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.guest.herbicorpsapp.Constants;
import com.example.guest.herbicorpsapp.R;
import com.example.guest.herbicorpsapp.adapters.RecipeListAdapter;
import com.example.guest.herbicorpsapp.models.Recipe;
import com.example.guest.herbicorpsapp.services.YummlyService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {
    @Bind(R.id.recipeRecyclerView) RecyclerView mRecipeRecyclerView;
    private static final String TAG = RecipeListActivity.class.getSimpleName();
    public ArrayList<Recipe> mRecipes;
    private RecipeListAdapter mAdapter;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentFood = mSharedPreferences.getString(Constants.PREFERENCES_FOOD_KEY, null);
//        Log.v("prefs", mRecentFood);

        if (mRecentFood != null) {
            getRecipes(mRecentFood);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getRecipes(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String foodSearch) {
        mEditor.putString(Constants.PREFERENCES_FOOD_KEY, foodSearch).apply();
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

                RecipeListActivity.this.runOnUiThread(new Runnable() {

//                    @Override
//                    public void run() {
//                        String[] recipeNames = new String[mRecipes.size()];
//                        for (int i = 0; i < recipeNames.length; i ++) {
//                            recipeNames[i] = mRecipes.get(i).getRecipeName();
//                        }
//
//                        ArrayAdapter adapter = new ArrayAdapter(RecipeListActivity.this, android.R.layout.simple_list_item_1, recipeNames);
//                        mRecipeListView.setAdapter(adapter);
//
//                        for (String recipeName : recipeNames) {
//                            Log.d("Attributes", "name: " + recipeName);
//                        }
//                    }
                    @Override
                    public void run() {
                        mAdapter = new RecipeListAdapter(getApplicationContext(), mRecipes);
                        mRecipeRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeListActivity.this);
                        mRecipeRecyclerView.setLayoutManager(layoutManager);
                        mRecipeRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
