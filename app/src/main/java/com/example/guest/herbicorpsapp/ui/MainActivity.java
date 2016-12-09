package com.example.guest.herbicorpsapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.herbicorpsapp.Constants;
import com.example.guest.herbicorpsapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mainTitle) TextView mMainTitle;
    @Bind(R.id.subTitle) TextView mSubTitle;
    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.ingredientInput) EditText mIngredientInput;
    @Bind(R.id.contactTextView) TextView mContactEditText;
    @Bind(R.id.favoritesButton) Button mFavoritesButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mMainTitle.setTypeface(pacificoFont);
        mSubTitle.setTypeface(pacificoFont);

        mFindButton.setOnClickListener(this);
        mFavoritesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindButton) {
            String ingredients = mIngredientInput.getText().toString();
            if (!(ingredients).equals("")) {
                addToSharedPreferences(ingredients);
            }
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            intent.putExtra("ingredients", ingredients);
            startActivity(intent);
        }
        if (v == mContactEditText) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 437-242-6777"));
            startActivity(intent);
        }
        if (v == mFavoritesButton) {
            Intent intent  = new Intent(MainActivity.this, FavoriteRecipeListActivity.class);
            startActivity(intent);
        }
    }
    private void addToSharedPreferences(String food) {
        mEditor.putString(Constants.PREFERENCES_FOOD_KEY, food).apply();
    }
}
