package com.web.recipes.dao;

import com.web.recipes.model.Recipes;

import java.security.Principal;
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

    Recipes updateRecipe (Recipes recipe, String username);


//    delete recipes

    Recipes deleteRecipes (int id);
}
