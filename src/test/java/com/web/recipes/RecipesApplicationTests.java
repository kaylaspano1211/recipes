package com.web.recipes;

import com.web.recipes.dao.JdbcRecipeDao;
import com.web.recipes.model.Recipes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RecipesApplicationTests extends BaseDaoTests{

	private JdbcRecipeDao recipeDao;

	private Recipes createdRecipe;

	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		recipeDao = new JdbcRecipeDao(jdbcTemplate);
		createdRecipe = new Recipes();
	}

	@Test
	public void retrieve_all_recipes_returns_list_all_recipes() {
		List<Recipes> recipes = recipeDao.retrieveAllRecipes();
		Assert.assertEquals(2, recipes.size());
	}

}
