package com.web.recipes.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Recipe not found.")
public class RecipeNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public RecipeNotFoundException() {
        super("Recipe not found.");
    }
}
