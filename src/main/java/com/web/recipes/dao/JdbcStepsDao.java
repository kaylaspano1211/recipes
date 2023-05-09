package com.web.recipes.dao;

import com.web.recipes.model.Steps;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStepsDao implements StepsDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcStepsDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Steps addStep(Steps step) {
        String sql = "INSERT INTO steps (recipe_id, step_number, step_description) " +
                    "VALUES (?,?,?) RETURNING step_id;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, step.getRecipeId(), step.getStepNumber(), step.getStepDescription());
        step.setStepId(id);
        return step;
    }

    @Override
    public List<Steps> getAllStepsInRecipe(int id) {
        List<Steps> steps = new ArrayList<>();

        String sql = "SELECT step_id, recipe_id, step_number, step_description " +
                "FROM steps WHERE recipe_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){
            Steps step = mapRowToSteps(results);
            steps.add(step);
        }
        return steps;
    }

    @Override
    public Steps getStepsById(int id) {

        String sql = "SELECT step_id, recipe_id, step_number, step_description " +
                "FROM steps WHERE step_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if(result.next()){
            return mapRowToSteps(result);
        } else {
            return null;
        }
    }

    @Override
    public void updateStep(Steps steps) {
        String sql = "UPDATE steps SET step_description = ? " +
                "WHERE recipe_id = ? AND step_id = ?;";

        jdbcTemplate.update(sql, steps.getStepDescription(), steps.getRecipeId(), steps.getStepId());
    }

    @Override
    public void deleteStepNumber(int number) {
        String sql = "DELETE FROM steps WHERE step_number = ?;";
        jdbcTemplate.update(sql, number);
    }

    @Override
    public void deleteAllSteps(int id) {
        String sql = "DELETE FROM steps WHERE recipe_id = ?;";
        jdbcTemplate.update(sql, id);
    }


    public Steps mapRowToSteps (SqlRowSet results) {
        Steps steps = new Steps();

        steps.setStepId(results.getInt("step_id"));
        steps.setRecipeId(results.getInt("recipe_id"));
        steps.setStepNumber(results.getInt("step_number"));
        steps.setStepDescription(results.getString("step_description"));

        return steps;
    }
}
