package com.web.recipes.dao;

import com.web.recipes.model.Ingredients;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcIngredientDao implements IngredientsDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Ingredients addIngredients(String ingredientName) {
        Ingredients ingredient = null;

        String sql = "INSERT INTO ingredients (ingredient_name) VALUES (?) RETURNING ingredient_id";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, ingredientName);

        if(id != null) {
            ingredient = new Ingredients();
            ingredient.setIngredientId(id);
            ingredient.setIngredientName(ingredientName);
        }
        return ingredient;
    }

    @Override
    public Ingredients retrieveIngredientById(int id) {
        Ingredients ingredient = null;

        String sql = "SELECT ingredient_id, ingredient_name FROM ingredients " +
                "WHERE ingredient_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if(result.next()) {
            ingredient = mapRowToIngredients(result);
        } else {
            return null;
        }
        return ingredient;
    }



    public Ingredients mapRowToIngredients(SqlRowSet results) {
        Ingredients ingredients = new Ingredients();

        ingredients.setIngredientId(results.getInt("ingredient_id"));
        ingredients.setIngredientName(results.getString("ingredient_name"));

        return ingredients;
    }
}
