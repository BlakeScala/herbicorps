package com.example.guest.herbicorpsapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mainTitle) TextView mMainTitle;
    @Bind(R.id.subTitle) TextView mSubTitle;
    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.ingredientInput) EditText mIngredientInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mMainTitle.setTypeface(pacificoFont);
        mSubTitle.setTypeface(pacificoFont);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindButton) {
            String ingredients = mIngredientInput.getText().toString();
            Intent intent = new Intent(MainActivity.this, RecipeSearch.class);
            intent.putExtra("ingredients", ingredients);
            startActivity(intent);
        }
    }
}
