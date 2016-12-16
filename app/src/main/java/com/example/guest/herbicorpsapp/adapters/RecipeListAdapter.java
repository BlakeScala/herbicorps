package com.example.guest.herbicorpsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.herbicorpsapp.Constants;
import com.example.guest.herbicorpsapp.R;
import com.example.guest.herbicorpsapp.models.Recipe;
import com.example.guest.herbicorpsapp.ui.RecipeDetailActivity;
import com.example.guest.herbicorpsapp.ui.RecipeDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private ArrayList<Recipe> mRecipes = new ArrayList<Recipe>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.recipeNameTextView) TextView mRecipeNameTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        @Bind(R.id.recipeImageView) ImageView mRecipeImageView;
        @Bind(R.id.estimatedTimeTextView) TextView mEstimatedTimeTextView;

        private Context context;
        private int mOrientation;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        public void bindRecipe(Recipe recipe) {
            Picasso.with(mContext)
                    .load(recipe.getImageURL())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mRecipeImageView);

            mRecipeNameTextView.setText(recipe.getRecipeName());
            mRatingTextView.setText("Rating: " + String.valueOf(recipe.getRating()) + "/5");
            mEstimatedTimeTextView.setText("Time: " + String.valueOf(recipe.getEstimatedTime()/60) + " minutes");
        }

        private void createDetailFragment(int position) {
            RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(mRecipes, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.recipeDetailContainer, detailFragment);
            ft.commit();
        }

        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(mRecipes));
                mContext.startActivity(intent);
            }
        }
    }
}
