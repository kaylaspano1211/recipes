package com.web.recipes.dao;

import com.web.recipes.model.Steps;

import java.util.List;

public interface StepsDao {

    Steps addStep (Steps step);

    List<Steps> getAllStepsInRecipe (int id);

    Steps getStepsById (int id);

    void updateStep (Steps steps);

    void deleteStepNumber(int number);

    void deleteAllSteps(int id);

}
