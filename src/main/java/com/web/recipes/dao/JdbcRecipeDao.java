package com.web.recipes.dao;


import com.web.recipes.model.Recipes;

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
        return null;
    }

    @Override
    public Recipes createRecipe(Recipes recipe) {
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
}
