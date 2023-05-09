BEGIN TRANSACTION;

DROP TABLE IF EXISTS quantities, steps, ingredients, measurements, recipes, users, images;


CREATE TABLE users (

	user_id serial,
	username varchar(50) not null,
	password_hash varchar(100) not null,
	user_role varchar(50) not null,

	CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE images (

	image_id serial,
	url varchar(500),

	CONSTRAINT pk_image PRIMARY KEY (image_id)
);

CREATE TABLE recipes (

	recipe_id serial,
	recipe_name varchar(100) not null,
	course varchar(100) not null,
	holidays varchar(100),
	food_category varchar(100) not null,
	short_description varchar(500) not null,
	prep_time int not null,
	cook_time int not null,
	user_id int not null,
	image_id int,

	CONSTRAINT pk_recipe PRIMARY KEY (recipe_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT fk_image_id FOREIGN KEY (image_id) REFERENCES images (image_id)

);

CREATE TABLE ingredients (

	ingredient_id serial,
	ingredient_name varchar(100) not null,

	CONSTRAINT pk_ingredient PRIMARY KEY (ingredient_id)
);

CREATE TABLE measurements (
	measurement_id serial,
	measurement_name varchar(50),

	CONSTRAINT pk_measurement PRIMARY KEY (measurement_id)
);

CREATE TABLE quantities (

	quantity_id serial,
	recipe_id int not null,
	ingredient_id int not null,
	measurement_id int,
	ingredient_quantity decimal,

	CONSTRAINT pk_quantity_id PRIMARY KEY (quantity_id),
	CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
	CONSTRAINT fk_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id),
	CONSTRAINT fk_measurement_id FOREIGN KEY (measurement_id) REFERENCES measurements (measurement_id)

);

CREATE TABLE steps (
	step_id serial,
	recipe_id int not null,
	step_number int,
	step_description varchar(500),

	CONSTRAINT pk_step PRIMARY KEY (step_id),
	CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)


);

INSERT INTO users (username,password_hash, user_role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'admin');
INSERT INTO users (username,password_hash, user_role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'admin');

-- default image id 1
INSERT INTO images (url) VALUES ('https://www.mdanderson.org/images/publications/focused-on-health/2019/web_healthy_cooking_1376x774.png.resize.702.404.jpg');
INSERT INTO images (url) VALUES ('https://twokooksinthekitchen.com/wp-content/uploads/2022/03/IMG_0309.jpg');

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


COMMIT;
