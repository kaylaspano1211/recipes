BEGIN TRANSACTION;

INSERT INTO users (username,password_hash) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');
INSERT INTO users (username,password_hash) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');

INSERT INTO recipes (recipe_name, course, holidays, food_category, short_description, prep_time, cook_time, user_id, image_id)
VALUES ('Stuffed Peppers', 'Dinner', 'Christmas', 'Italian', 'The perfect vessel for a variety of flavors', 20, 35, 1, null);
INSERT INTO recipes (recipe_name, course, holidays, food_category, short_description, prep_time, cook_time, user_id, image_id)
VALUES ('Beef Stew', 'Dinner', null, 'Dutch', 'Cold weather essential', 15, 100, 2, 1);

INSERT INTO ingredients (ingredient_name) VALUES ('vegetable oil');
INSERT INTO ingredients (ingredient_name) VALUES ('beef stew meat');
INSERT INTO ingredients (ingredient_name) VALUES ('olive oil');
INSERT INTO ingredients (ingredient_name) VALUES ('yellow onion');
INSERT INTO ingredients (ingredient_name) VALUES ('carrots');
INSERT INTO ingredients (ingredient_name) VALUES ('celery');
INSERT INTO ingredients (ingredient_name) VALUES ('kosher salt');
INSERT INTO ingredients (ingredient_name) VALUES ('ground black pepper');
INSERT INTO ingredients (ingredient_name) VALUES ('garlic');
INSERT INTO ingredients (ingredient_name) VALUES ('tomato paste');
INSERT INTO ingredients (ingredient_name) VALUES ('low-sodium beef broth');
INSERT INTO ingredients (ingredient_name) VALUES ('red wine');
INSERT INTO ingredients (ingredient_name) VALUES ('worcestershire sauce');
INSERT INTO ingredients (ingredient_name) VALUES ('thyme');
INSERT INTO ingredients (ingredient_name) VALUES ('bay leaves');
INSERT INTO ingredients (ingredient_name) VALUES ('potatoes');
INSERT INTO ingredients (ingredient_name) VALUES ('peas');
INSERT INTO ingredients (ingredient_name) VALUES ('parsley');

INSERT INTO measurements (measurement_name) VALUES ('tbsp');
INSERT INTO measurements (measurement_name) VALUES ('tsp');
INSERT INTO measurements (measurement_name) VALUES ('c');
INSERT INTO measurements (measurement_name) VALUES ('lb');


INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 1, 1, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 2, 4, 2);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 3, 1, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 4, null, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 5, null, 2);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 6, null, 2);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 7, null, null);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 8, null, null);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 9, null, 3);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 10, 3, .25);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 11, 3, 6);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 12, 3, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 13, 1, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 14, 2, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 15, null, 2);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 16, 4, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 17, 3, 1);
INSERT INTO quantities (recipe_id, ingredient_id, measurement_id, ingredient_quantity) VALUES (2, 18, 3, .25);

INSERT INTO images (url) VALUES ('https://twokooksinthekitchen.com/wp-content/uploads/2022/03/IMG_0309.jpg');



COMMIT;

