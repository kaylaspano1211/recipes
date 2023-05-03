package com.web.recipes.controller;

import com.web.recipes.dao.RecipeDao;
import com.web.recipes.model.Recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
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
        return recipeDao.retrieveRecipeById(id);
    }




//    post recipes
    @ResponseStatus (HttpStatus.CREATED)
    @RequestMapping (path = "/recipes", method = RequestMethod.POST)
    public Recipes createRecipe (@RequestBody Recipes recipe, Principal principal) {
        return recipeDao.createRecipe(recipe, principal.getName());
    }


//    update recipes by id
    @RequestMapping (path = "/recipes/{id}", method = RequestMethod.POST)
    public Recipes updateRecipe (@RequestBody Recipes recipe, @PathVariable int id) {
        return recipeDao.updateRecipe(recipe, id);
    }



//    delete recipes
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @RequestMapping (path = "/recipes/{id}", method = RequestMethod.DELETE)
    public void deleteRecipe (@PathVariable int id, Principal principal) {}

}
