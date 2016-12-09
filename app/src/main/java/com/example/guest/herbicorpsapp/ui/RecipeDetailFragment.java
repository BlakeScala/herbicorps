package com.example.guest.herbicorpsapp.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.herbicorpsapp.Constants;
import com.example.guest.herbicorpsapp.R;
import com.example.guest.herbicorpsapp.models.Recipe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.recipeImageView) ImageView mImageLabel;
    @Bind(R.id.recipeNameTextView) TextView mNameLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.estimatedTimeTextView) TextView mTimeLabel;
    @Bind(R.id.ingredientTextView) TextView mIngredientLabel;
    @Bind(R.id.checkButton) Button mCheckButton;
    @Bind(R.id.xButton) Button mXButton;

    private Recipe mRecipe;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = Parcels.unwrap(getArguments().getParcelable("recipe"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mRecipe.getImageURL())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mRecipe.getRecipeName());
        mRatingLabel.setText("Rating: " + String.valueOf(mRecipe.getRating()) + "/5");
        mTimeLabel.setText("Preparation: " + String.valueOf(mRecipe.getEstimatedTime()/60) + " minutes");
        mIngredientLabel.setText(android.text.TextUtils.join(", ", mRecipe.getIngredients()));

        mCheckButton.setOnClickListener(this);
        mXButton.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if (v == mCheckButton) {
            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES);
            recipeRef.push().setValue(mRecipe);
            Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_LONG).show();
        } else if (v == mXButton) {
            Toast.makeText(getContext(), "Not interested", Toast.LENGTH_LONG).show();

        }
    }

}
