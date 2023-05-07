package com.web.recipes.dao;

import com.web.recipes.model.Ingredients;

public interface IngredientsDao {

    Ingredients addIngredients (String ingredientName);

    Ingredients retrieveIngredientById (int id);

}
