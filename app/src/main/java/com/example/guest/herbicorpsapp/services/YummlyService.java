package com.example.guest.herbicorpsapp.services;

import android.util.Log;

import com.example.guest.herbicorpsapp.Constants;
import com.example.guest.herbicorpsapp.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Guest on 12/1/16.
 */
public class YummlyService {

    public static void findRecipes(String foodEntry, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.APP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.APP_ID_QUERY_PARAMETER, Constants.APP_ID);
        urlBuilder.addQueryParameter(Constants.APP_KEY_QUERY_PARAMETER, Constants.APP_KEY);
        urlBuilder.addQueryParameter(Constants.APP_FOOD_QUERY_PARAMETER, foodEntry);

        String url = urlBuilder.build().toString();
        String newUrl = url + "&allowedDiet[]=386^Vegan";

        Request request = new Request.Builder()
                .url(newUrl)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Recipe> processResults(Response response) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yummlyJSON = new JSONObject(jsonData);
                JSONArray matchesJSON = yummlyJSON.getJSONArray("matches");
                for(int i = 0; i < matchesJSON.length(); i++) {
                    JSONObject recipeJSON = matchesJSON.getJSONObject(i);
                    String recipeName = recipeJSON.getString("recipeName");
                    String sourceName = recipeJSON.getString("sourceDisplayName");
                    String imageURL = recipeJSON.getJSONObject("imageUrlsBySize").getString("90");

                    ArrayList<String> ingredients = new ArrayList<>();
                    JSONArray ingredientsJSON = recipeJSON.getJSONArray("ingredients");
                    for(int j = 0; j < ingredientsJSON.length(); j++) {
                        ingredients.add(ingredientsJSON.get(j).toString());
                    }

                    int estimatedTime = recipeJSON.getInt("totalTimeInSeconds");
                    int rating = recipeJSON.getInt("rating");
                    String id = recipeJSON.getString("id");

                    Recipe recipe = new Recipe(recipeName, sourceName, imageURL, ingredients, estimatedTime, rating, id);
                    recipes.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }


}













//http://api.yummly.com/v1/api/recipes?_app_id=98609b53&_app_key=765f89e001c44fd2193e307b33c12402&q=onion+soup &allowedDiet[]=386^Vegan
