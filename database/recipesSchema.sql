BEGIN TRANSACTION;

DROP TABLE IF EXISTS quantities, steps, recipes, users, images, ingredients, measurements;


CREATE TABLE users (

	user_id serial,
	username varchar(50) not null,
	password_hash varchar(100) not null,
	
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
	prep_time time not null,
	cook_time time not null,
	user_id int not null,
	image_id int,
	
	CONSTRAINT pk_recipe PRIMARY KEY (recipe_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT fk_image_id FOREIGN KEY (image_id) REFERENCES images (image_id)
		
);

CREATE TABLE ingredients (

	ingredient_id serial,
	ingredient_name varchar(50) not null,

	CONSTRAINT pk_ingredient PRIMARY KEY (ingredient_id)
);

CREATE TABLE measurements (
	measurement_id serial,
	measurement_name varchar(50) not null,

	CONSTRAINT pk_measurement PRIMARY KEY (measurement_id)
);

CREATE TABLE quantities (

	quantity_id serial,
	recipe_id int not null,
	ingredient_id int not null,
	measurement_id int not null,
	ingredient_quantity float not null,

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

COMMIT;