package com.web.recipes.dao;

import com.web.recipes.model.Steps;

import java.util.List;

public interface StepsDao {

    Steps addStep (Steps step);

    List<Steps> getAllStepsInRecipe (int id);

    Steps getStepsById (int id);

    void updateStep (String stepDescription, int id);

    void deleteStepNumber(int id);

    void deleteAllSteps(int id);

}
