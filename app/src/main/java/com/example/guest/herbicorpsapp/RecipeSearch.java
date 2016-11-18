package com.example.guest.herbicorpsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeSearch extends AppCompatActivity {
    @Bind(R.id.searchText) TextView mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String ingredientsInput = intent.getStringExtra("ingredients").toString();
        mSearchText.setText("Vegan meals including " + ingredientsInput);
        Toast.makeText(RecipeSearch.this, "API Call", Toast.LENGTH_LONG).show();
    }
}
