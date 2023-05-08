package com.web.recipes.dao;

import com.web.recipes.model.Quantities;

public interface QuantitiesDao {

    Quantities addQuantity (Quantities quantity);

    void updateQuantity (double quantity, int id);

    Quantities getQuantityById (int id);

    Quantities getQuantityByRecipeId (int id);

    Quantities getQuantityByIngredientId (int id);

    Quantities getQuantityByMeasurementId (int id);

}
