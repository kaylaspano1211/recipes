package com.web.recipes.controller;

import com.web.recipes.dao.*;
import com.web.recipes.model.*;
import com.web.recipes.security.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@Validated
public class RecipesController {

    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private IngredientsDao ingredientsDao;
    @Autowired
    private ImagesDao imagesDao;
    @Autowired
    private MeasurementDao measurementDao;
    @Autowired
    private QuantitiesDao quantitiesDao;
    @Autowired
    private StepsDao stepsDao;


//    get all recipes
    @RequestMapping (path = "/recipes", method = RequestMethod.GET)
    public List<Recipes> retrieveAllRecipes () {

        return recipeDao.retrieveAllRecipes();
    }

//    get recipes by id

    @RequestMapping (path = "/recipes/{id}", method = RequestMethod.GET)
    public Recipes retrieveRecipeById (@PathVariable int id) {
        Recipes recipe = recipeDao.retrieveRecipeById(id);
        return recipe;
    }

    @RequestMapping (path = "/recipes/user", method = RequestMethod.GET)
    public Recipes retrieveRecipeByUsername(@RequestParam String username) {
        Recipes recipe = recipeDao.retrieveRecipeByUsername(username);
        return recipe;
    }

//    post recipes
    @ResponseStatus (HttpStatus.CREATED)
    @RequestMapping (path = "/recipes", method = RequestMethod.POST)
    public Recipes createRecipe (@Valid @RequestBody Recipes recipe) {

        return recipeDao.createRecipe(recipe);
    }


//    update recipes by id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping (path = "/recipes/{id}", method = RequestMethod.PUT)
    public void updateRecipe (@Valid @RequestBody Recipes recipe, @PathVariable("id") int id, Principal user) {
       try {
            recipeDao.updateRecipe(recipe, id);
       } catch (RecipeNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe doesn't exist");
       }
    }

//    delete recipes
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @RequestMapping (path = "/recipes/{id}", method = RequestMethod.DELETE)
    public void deleteRecipe (@PathVariable int id, Principal principal) throws RecipeNotFoundException {
         recipeDao.deleteRecipes(id);
    }

//  add ingredient
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (path = "/ingredients/{ingredientName}", method = RequestMethod.POST)
    public Ingredients addIngredients (@PathVariable String ingredientName) {
        return ingredientsDao.addIngredients(ingredientName);
    }

//    get ingredient by id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/ingredients/{id}", method = RequestMethod.GET)
    public Ingredients getIngredientById (@PathVariable int id){
        return ingredientsDao.retrieveIngredientById(id);
    }

//    get ingredient by name
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/ingredients/name", method = RequestMethod.GET)
    public Ingredients getIngredientByName (@RequestParam String ingredientName) {
        return ingredientsDao.retrieveIngredientsByName(ingredientName);
    }

//    add image
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/images/{url}", method = RequestMethod.POST)
    public Images addImage (@PathVariable String url){
        return imagesDao.addImage(url);
    }

//    get image by id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/images/{id}", method = RequestMethod.GET)
    public Images retrieveImageById (@PathVariable int id){
        return imagesDao.getImageById(id);
    }

//    get measurement by id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/measurements/{id}", method = RequestMethod.GET)
    public Measurements retrieveMeasurementById (@PathVariable int id) {
        return measurementDao.retrieveMeasurementById(id);
    }

//    get measurement by name
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/measurements/name/{measurementName}", method = RequestMethod.GET)
    public Measurements retrieveMeasurementByName (@PathVariable String measurementName) {
        return measurementDao.retrieveMeasurementByName(measurementName);
    }

//    add quantity
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/quantities", method = RequestMethod.POST)
    public Quantities addQuantities (@Valid @RequestBody Quantities quantities) {
        return quantitiesDao.addQuantity(quantities);
    }

//    update quantities
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/quantities/{id}", method = RequestMethod.PUT)
    public void updateQuantities (double quantity, @PathVariable int id){
        try {
            quantitiesDao.updateQuantity(quantity, id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity id doesn't exist");
        }
    }

//    get quantity by id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/quantities/{id}", method = RequestMethod.GET)
    public Quantities retrieveQuantityById (@PathVariable int id) {
        return quantitiesDao.getQuantityById(id);
    }

    //    get quantity by recipe id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/quantities/recipes/{id}", method = RequestMethod.GET)
    public List<Quantities> retrieveQuantityByRecipeId (@PathVariable int id) {
        return quantitiesDao.getQuantityByRecipeId(id);
    }

    //    get quantity by ingredient id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/quantities/ingredients/{id}", method = RequestMethod.GET)
    public Quantities retrieveQuantityByIngredientId (@PathVariable int id) {
        return quantitiesDao.getQuantityByIngredientId(id);
    }

    //    get quantity by measurement id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/quantities/measurements/{id}", method = RequestMethod.GET)
    public List<Quantities> retrieveQuantityByMeasurementId (@PathVariable int id) {
        return quantitiesDao.getQuantityByMeasurementId(id);
    }

//    add a step
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/steps", method = RequestMethod.POST)
    public Steps addStep (@Valid@RequestBody Steps steps) {
        return stepsDao.addStep(steps);
    }

//    get all steps in recipe
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/steps/recipe/{id}", method = RequestMethod.GET)
    public List<Steps> allStepsInRecipe (@PathVariable int id) {
        return stepsDao.getAllStepsInRecipe(id);
    }

//    get steps by id
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/steps/{id}", method = RequestMethod.GET)
    public Steps getStepById(@PathVariable int id){
        return stepsDao.getStepsById(id);
    }

//    update a step
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/steps/{recipeId}", method = RequestMethod.PUT)
    public void updateStep(@Valid @RequestBody Steps steps, @PathVariable int recipeId){
        try {
            stepsDao.updateStep(steps);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Step doesn't exist");
        }
    }

//    delete a step
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/steps/number/{number}", method = RequestMethod.DELETE)
    public void deleteStepNumber(@PathVariable int number){
        try {
            stepsDao.deleteStepNumber(number);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Step doesn't exist");
        }
    }

//    delete all steps
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/steps/{id}", method = RequestMethod.DELETE)
    public void deleteAllSteps(@PathVariable int id){
        try {
            stepsDao.deleteAllSteps(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Steps not found");
        }
    }


}
