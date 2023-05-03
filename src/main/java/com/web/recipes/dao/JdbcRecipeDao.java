package com.web.recipes.dao;


import com.web.recipes.model.Recipes;

import java.security.Principal;
import java.util.List;

public class JdbcRecipeDao<JdbcTemplate> implements RecipeDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Recipes> retrieveAllRecipes() {

        return null;
    }

    @Override
    public Recipes retrieveRecipeById(int id) {
        Recipes recipe;
        String sql =
        return null;
    }

    @Override
    public Recipes createRecipe(Recipes recipe, String username) {
        return null;
    }

    @Override
    public Recipes updateRecipe(Recipes recipe, int id) {
        return null;
    }

    @Override
    public Recipes deleteRecipes(int id) {
        return null;
    }

    private Recipes mapRowToRecipe(SqlRowSet result) {
        Recipes recipe = new Recipes();

        recipe.setRecipeId(result.getInt("recipeId"));
        recipe.setRecipeName(result.getString("recipeName"));
        recipe.setCourse(result.getString("course"));
        recipe.setHolidays(result.getString("holidays"));
        recipe.setFoodCategory(result.getString("foodCategory"));
        recipe.setDescription(result.getString("description"));
        recipe.setPrepTime(result.getInt("prepTime"));
        recipe.setCookTime(result.getInt("cookTime"));
        recipe.setUserId(result.getInt("userId"));
        recipe.setImageId(result.getInt("imageId"));

        return recipe;
    }
}
