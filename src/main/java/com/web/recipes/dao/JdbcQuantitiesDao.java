package com.web.recipes.dao;

import com.web.recipes.model.Quantities;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcQuantitiesDao implements QuantitiesDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcQuantitiesDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Quantities addQuantity(Quantities quantity) {
        String sql = "INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) " +
                    "VALUES (?,?,?,?) RETURNING quantity_id;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, quantity.getRecipeId(), quantity.getIngredientId(),
                                                quantity.getMeasurementId(), quantity.getIngredientQuantity());
        quantity.setQuantityId(id);
        return quantity;
    }

    @Override
    public void updateQuantity(double quantity, int id) {
        String sql = "UPDATE quantities SET ingredient_quantity = ? " +
                    "WHERE quantity_id = ?";
        jdbcTemplate.update(sql, quantity, id);
    }

    @Override
    public Quantities getQuantityById(int id) {
        String sql = "SELECT quantity_id, recipe_id, ingredient_id, measurement_id, ingredient_quantity FROM quantities " +
                "WHERE quantity_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if(results.next()){
            return mapRowToQuantities(results);
        } else {
            return null;
        }
    }

    @Override
    public List<Quantities> getQuantityByRecipeId(int id) {
        List<Quantities> quantities = new ArrayList<>();
        String sql = "SELECT quantity_id, recipe_id, ingredient_id, measurement_id, ingredient_quantity FROM quantities " +
                "WHERE recipe_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){
            Quantities quantity = mapRowToQuantities(results);
            quantities.add(quantity);
        }
        return quantities;
    }

    @Override
    public Quantities getQuantityByIngredientId(int id) {
        String sql = "SELECT quantity_id, recipe_id, ingredient_id, measurement_id, ingredient_quantity FROM quantities " +
                "WHERE ingredient_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if(results.next()){
            return mapRowToQuantities(results);
        } else {
            return null;
        }
    }

    @Override
    public List<Quantities> getQuantityByMeasurementId(int id) {
        List<Quantities> quantities = new ArrayList<>();
        String sql = "SELECT quantity_id, recipe_id, ingredient_id, measurement_id, ingredient_quantity FROM quantities " +
                "WHERE measurement_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){
            Quantities quantity = mapRowToQuantities(results);
            quantities.add(quantity);
        }
        return quantities;
    }


    public Quantities mapRowToQuantities(SqlRowSet results) {
        Quantities quantities = new Quantities();

        quantities.setQuantityId(results.getInt("quantity_id"));
        quantities.setRecipeId(results.getInt("recipe_id"));
        quantities.setIngredientId(results.getInt("ingredient_id"));
        quantities.setMeasurementId(results.getInt("measurement_id"));
        quantities.setIngredientQuantity(results.getDouble("ingredient_quantity"));

        return quantities;
    }
}
