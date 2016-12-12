package com.example.guest.herbicorpsapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.herbicorpsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mainTitle) TextView mMainTitle;
    @Bind(R.id.subTitle) TextView mSubTitle;
    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.ingredientInput) EditText mIngredientInput;
    @Bind(R.id.contactTextView) TextView mContactEditText;
    @Bind(R.id.favoritesButton) Button mFavoritesButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

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
//            if (!(ingredients).equals("")) {
//                addToSharedPreferences(ingredients);
//            }
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
//    private void addToSharedPreferences(String food) {
//        mEditor.putString(Constants.PREFERENCES_FOOD_KEY, food).apply();
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
