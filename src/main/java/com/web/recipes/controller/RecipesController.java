package com.web.recipes.controller;

import com.web.recipes.dao.ImagesDao;
import com.web.recipes.dao.IngredientsDao;
import com.web.recipes.dao.MeasurementDao;
import com.web.recipes.dao.RecipeDao;
import com.web.recipes.model.Images;
import com.web.recipes.model.Ingredients;
import com.web.recipes.model.Measurements;
import com.web.recipes.model.Recipes;
import com.web.recipes.security.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

}
