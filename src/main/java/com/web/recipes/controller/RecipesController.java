package com.web.recipes.controller;

import com.web.recipes.dao.RecipeDao;
import com.web.recipes.model.Recipes;
import com.web.recipes.security.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@Validated
public class RecipesController {

    @Autowired
    private RecipeDao recipeDao;


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
    public Recipes createRecipe (@RequestBody Recipes recipe) {
        return recipeDao.createRecipe(recipe);
    }


//    update recipes by id
    @RequestMapping (path = "/recipes/{id}", method = RequestMethod.POST)
    public Recipes updateRecipe (@RequestBody Recipes recipe, @PathVariable String username) {
        return recipeDao.updateRecipe(recipe, username);
    }

//    delete recipes
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @RequestMapping (path = "/recipes/{id}", method = RequestMethod.DELETE)
    public void deleteRecipe (@PathVariable int id, Principal principal) throws RecipeNotFoundException {
         recipeDao.deleteRecipes(id);
    }

}
