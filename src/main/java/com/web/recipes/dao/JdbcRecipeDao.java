package com.web.recipes.dao;


import com.web.recipes.model.Recipes;
import com.web.recipes.model.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcRecipeDao implements RecipeDao{

    private final JdbcTemplate jdbcTemplate;
    private ImagesDao imagesDao;
    private UsersDao userDao;


    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        imagesDao = new JdbcImagesDao(jdbcTemplate);
        userDao = new JdbcUsersDao(jdbcTemplate);
    }



    @Override
    public List<Recipes> retrieveAllRecipes() {
        List<Recipes> recipes = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_name, course, holidays, food_category, short_description, prep_time, " +
                "cook_time, user_id, image_id " +
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

        String sql = "SELECT recipe_id, recipe_name, course, holidays, food_category, short_description, " +
                "prep_time, cook_time, user_id, image_id " +
                "FROM recipes WHERE recipe_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()) {
            return mapRowToRecipe(results);
        } else {
            return null;
        }
    }

    @Override
    public Recipes retrieveRecipeByUsername(String username) {
        Recipes recipe = null;
        String sql = "SELECT recipe_id, recipe_name, course, holidays, food_category, short_description, " +
                "prep_time, cook_time, user_id, image_id " +
                "FROM recipes WHERE user_id = (SELECT user_id FROM users WHERE username = ?);";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if(results.next()) {
            recipe = mapRowToRecipe(results);
            recipe.setRecipeId(results.getInt("id"));
        }
        return recipe;

    }


//TODO fix image id error message

    @Override
    public Recipes createRecipe(Recipes recipe) {

        String sql = "INSERT INTO recipes (recipe_name, course, holidays, food_category, short_description, " +
                "prep_time, cook_time, user_id, image_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?) RETURNING recipe_id;";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, recipe.getRecipeName(), recipe.getCourse(), recipe.getHolidays(), recipe.getFoodCategory(),
                                                recipe.getDescription(), recipe.getPrepTime(), recipe.getCookTime(), recipe.getUserId(), recipe.getImageId());
        recipe.setRecipeId(id);
        return recipe;
    }


    @Override
    public Recipes updateRecipe(Recipes recipe, String username) {

        Recipes currentRecipe = this.retrieveRecipeByUsername(username);

        if (currentRecipe == null) {
            return null;
        } else {
//            String sql = "UPDATE recipe "
        }
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
