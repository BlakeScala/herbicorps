package com.example.guest.herbicorpsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.herbicorpsapp.Constants;
import com.example.guest.herbicorpsapp.R;
import com.example.guest.herbicorpsapp.models.Recipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Guest on 12/9/16.
 */
public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mRecipeImageView;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindRecipe(Recipe recipe) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.recipeNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView timeTextView = (TextView) mView.findViewById(R.id.estimatedTimeTextView);
        mRecipeImageView = (ImageView) mView.findViewById(R.id.recipeImageView);

        Picasso.with(mContext)
                .load(recipe.getImageURL())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mRecipeImageView);

        nameTextView.setText(recipe.getRecipeName());
        ratingTextView.setText("Rating: " + recipe.getRating() + "/5");
        timeTextView.setText("Time: " + String.valueOf(recipe.getEstimatedTime()/60) + " minutes");
    }
}
