package com.web.recipes.model;

import java.sql.Time;

public class Recipes {

    private int recipeId;
    private String recipeName;
    private String course;
    private String holidays;
    private String foodCategory;
    private String description;
    private Time prepTime;
    private Time cookTime;
    private int userId;
    private int imageId;

    public Recipes(int recipeId, String recipeName, String course, String holidays, String foodCategory,
                   String description, Time prepTime, Time cookTime, int userId, int imageId) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.course = course;
        this.holidays = holidays;
        this.foodCategory = foodCategory;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.userId = userId;
        this.imageId = imageId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getHolidays() {
        return holidays;
    }

    public void setHolidays(String holidays) {
        this.holidays = holidays;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Time prepTime) {
        this.prepTime = prepTime;
    }

    public Time getCookTime() {
        return cookTime;
    }

    public void setCookTime(Time cookTime) {
        this.cookTime = cookTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
