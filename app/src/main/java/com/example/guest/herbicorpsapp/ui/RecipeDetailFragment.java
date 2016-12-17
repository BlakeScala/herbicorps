package com.example.guest.herbicorpsapp.ui;


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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

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
    private String mSrc;

    private Recipe mRecipe;
    private ArrayList<Recipe> mRecipes;
    private int mPosition;

    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    public static RecipeDetailFragment newInstance(ArrayList<Recipe> recipes, Integer position, String src) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(recipes));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, src);

        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipes = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_RECIPES));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mRecipe = mRecipes.get(mPosition);
        mSrc = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        if (mSrc.equals(Constants.SOURCE_SAVED)) {
            mCheckButton.setVisibility(View.GONE);
        } else {
            mCheckButton.setOnClickListener(this);
        }

        Picasso.with(view.getContext())
                .load(mRecipe.getImageURL())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mRecipe.getRecipeName());
        mRatingLabel.setText("Rating: " + String.valueOf(mRecipe.getRating()) + "/5");
        mTimeLabel.setText("Preparation: " + String.valueOf(mRecipe.getEstimatedTime()/60) + " minutes");
        mIngredientLabel.setText(android.text.TextUtils.join(", ", mRecipe.getIngredients()));


        return view;

    }

    @Override
    public void onClick(View v) {
        if (v == mCheckButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES)
                    .child(uid);

            DatabaseReference pushRef = recipeRef.push();
            String pushId = pushRef.getKey();
            mRecipe.setPushId(pushId);
            pushRef.setValue(mRecipe);

            Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_LONG).show();
        }
    }

}
