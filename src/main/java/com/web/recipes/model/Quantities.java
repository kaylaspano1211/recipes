package com.web.recipes.model;

public class Quantities {

    private int quantityId;
    private int recipeId;
    private int ingredientId;
    private int measurementId;
    private double ingredientQuantity;

    public Quantities(int quantityId, int recipeId, int ingredientId, int measurementId, double ingredientQuantity) {
        this.quantityId = quantityId;
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.measurementId = measurementId;
        this.ingredientQuantity = ingredientQuantity;
    }

    public Quantities() {

    }

    public int getQuantityId() {

        return quantityId;
    }

    public void setQuantityId(int quantityId) {

        this.quantityId = quantityId;
    }

    public int getRecipeId() {

        return recipeId;
    }

    public void setRecipeId(int recipeId) {

        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }
}
