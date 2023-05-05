package com.web.recipes.dao;


import com.web.recipes.model.Recipes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcRecipeDao implements RecipeDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Recipes> retrieveAllRecipes() {
        List<Recipes> recipes = new ArrayList<>();
        String sql = "SELECT recipe_name, course, holidays, food_category, short_description, prep_time, cook_time, user_id, image_id " +
                "FROM recipes ORDER BY recipe_name ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Recipes recipe = mapRowToRecipe(results);
            recipes.add(recipe);
        }

        return recipes;
    }

    @Override
    public Recipes retrieveRecipeById(int id) {
        Recipes recipe;
        String sql = "SELECT recipe_name, course, holidays, food_category, short_description, prep_time, cook_time, user_id, image_id " +
                "FROM recipes WHERE recipe_id = ? ;";
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

        recipe.setRecipeId(result.getInt("recipe_id"));
        recipe.setRecipeName(result.getString("recipe_name"));
        recipe.setCourse(result.getString("course"));
        recipe.setHolidays(result.getString("holidays"));
        recipe.setFoodCategory(result.getString("food_category"));
        recipe.setDescription(result.getString("short_description"));
        recipe.setPrepTime(result.getInt("prep_time"));
        recipe.setCookTime(result.getInt("cook_time"));
        recipe.setUserId(result.getInt("user_id"));
        recipe.setImageId(result.getInt("image_id"));

        return recipe;
    }
}
