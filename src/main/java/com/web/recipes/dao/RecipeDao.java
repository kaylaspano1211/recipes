package com.web.recipes.dao;

import com.web.recipes.model.Recipes;

import java.security.Principal;
import java.util.List;

public interface RecipeDao {

    //    get all recipes

    List<Recipes> retrieveAllRecipes ();


//    get recipes by id

    Recipes retrieveRecipeById (int id);


//    post recipes

    Recipes createRecipe (Recipes recipe, String username);


//    update recipes by id

    Recipes updateRecipe (Recipes recipe, int id);


//    delete recipes

    Recipes deleteRecipes (int id);
}
