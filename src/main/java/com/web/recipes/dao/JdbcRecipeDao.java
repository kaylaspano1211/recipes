package com.web.recipes.dao;


import com.web.recipes.model.Images;
import com.web.recipes.model.Ingredients;
import com.web.recipes.model.Recipes;
import com.web.recipes.security.RecipeNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcRecipeDao implements RecipeDao{

    private final JdbcTemplate jdbcTemplate;
    private ImagesDao imagesDao;
    private UsersDao userDao;

    private final int DEFALUT_IMAGE_ID = 1;
    private final String DEFAULT_IMAGE_URL = "https://www.mdanderson.org/images/publications/focused-on-health/2019/web_healthy_cooking_1376x774.png.resize.702.404.jpg";



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
            recipe.setRecipeId(results.getInt("user_id"));
        }
        return recipe;

    }


    @Override
    public Recipes createRecipe(Recipes recipe) {

        String sql = "INSERT INTO recipes (recipe_name, course, holidays, food_category, short_description, " +
                "prep_time, cook_time, user_id, image_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?) RETURNING recipe_id;";

        if(recipe.getImage() != null && !recipe.getImage().getUrl().isBlank()) {
            //add image to database
            Images savedImage = imagesDao.addImage(recipe.getImage().getUrl());

            //add recipe to database
            Integer id = jdbcTemplate.queryForObject(sql, Integer.class, recipe.getRecipeName(), recipe.getCourse(), recipe.getHolidays(), recipe.getFoodCategory(),
                    recipe.getDescription(), recipe.getPrepTime(), recipe.getCookTime(), recipe.getUserId(), savedImage.getImageId());
            recipe.setRecipeId(id);
        } else {
            //add default image to recipe
            Images defaultImage = imagesDao.getImageById(DEFALUT_IMAGE_ID);
            recipe.setImage(defaultImage);

            //add recipe to database
            Integer id = jdbcTemplate.queryForObject(sql, Integer.class, recipe.getRecipeName(), recipe.getCourse(), recipe.getHolidays(), recipe.getFoodCategory(),
                    recipe.getDescription(), recipe.getPrepTime(), recipe.getCookTime(), recipe.getUserId(), DEFALUT_IMAGE_ID);
            recipe.setRecipeId(id);
        }

        return recipe;
    }


    @Override
    public void updateRecipe(Recipes recipe, int id) throws RecipeNotFoundException {

        Recipes currentRecipe = this.retrieveRecipeById(id);

        if (currentRecipe == null) {
            throw new RecipeNotFoundException();
        } else {
            String sql = "UPDATE recipes " +
                    "SET recipe_name = ?, "+
                    "course = ?, " +
                    "holidays = ?, " +
                    "food_category = ?, " +
                    "short_description = ?, " +
                    "prep_time = ?, " +
                    "cook_time = ?, " +
                    "user_id = ?, " +
                    "image_id = ? " +
                    "WHERE recipe_id = ?;";

            jdbcTemplate.update(sql, recipe.getRecipeName(), recipe.getCourse(), recipe.getHolidays(), recipe.getFoodCategory(),
                                recipe.getDescription(), recipe.getPrepTime(), recipe.getCookTime(),recipe.getUserId(), recipe.getImage(), id);

        }
    }

    @Override
    public void deleteRecipes(int id) throws RecipeNotFoundException { //delete from quantities and steps first
        String sql = "SELECT recipe_id, recipe_name, course, holidays, food_category, short_description, " +
                "prep_time, cook_time, user_id, image_id FROM recipes WHERE recipe_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if (result.next()) {
            id = result.getInt("recipe_id");
        }
        else {
            throw new RecipeNotFoundException();
        }

        //first delete the steps and quantities
        String stepsSQL = "DELETE FROM steps WHERE recipe_id = ?;";
        jdbcTemplate.update(stepsSQL, id);

        String quantitySQL = "DELETE FROM quantities WHERE recipe_id = ?;";
        jdbcTemplate.update(quantitySQL, id);

        //now delete the recipe
        String recipeSql = "DELETE FROM recipes WHERE recipe_id = ?;";
        jdbcTemplate.update(recipeSql, id);
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
        Integer imageId = result.getInt("image_id");
        if (imageId != null) {
            recipe.setImage(imagesDao.getImageById(imageId));
        }

        return recipe;
    }
}
