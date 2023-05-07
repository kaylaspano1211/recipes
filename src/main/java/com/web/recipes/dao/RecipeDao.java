package com.web.recipes.dao;

import com.web.recipes.model.Recipes;
import com.web.recipes.security.RecipeNotFoundException;

import java.util.List;

public interface RecipeDao {

    //    get all recipes

    List<Recipes> retrieveAllRecipes ();


//    get recipes by id

    Recipes retrieveRecipeById (int id);

    Recipes retrieveRecipeByUsername (String username);


//    post recipes

    Recipes createRecipe (Recipes recipe);


//    update recipes by id

    void updateRecipe (Recipes recipe, String username, int id) throws RecipeNotFoundException;


//    delete recipes

    void deleteRecipes (int id) throws RecipeNotFoundException;
}
