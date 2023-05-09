package com.web.recipes.dao;

import com.web.recipes.model.Quantities;

import java.util.List;

public interface QuantitiesDao {

    Quantities addQuantity (Quantities quantity);

    void updateQuantity (double quantity, int id);

    Quantities getQuantityById (int id);

    List<Quantities> getQuantityByRecipeId (int id);

    Quantities getQuantityByIngredientId (int id);

    List<Quantities> getQuantityByMeasurementId (int id);

}
