CREATE TABLE role (
	id SERIAL PRIMARY KEY,
	role VARCHAR(16) NOT NULL UNIQUE
);

INSERT INTO role VALUES (1, 'USER');
INSERT INTO role VALUES (2, 'ESTABLISHMENT');
INSERT INTO role VALUES (3, 'ADMIN');

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name VARCHAR(64) NOT NULL,
	email VARCHAR(64),
	address VARCHAR(64),
	birth DATE,
	premium BOOLEAN DEFAULT false,
	zone VARCHAR(32),
	city VARCHAR(32)
);

CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	category VARCHAR(32) NOT NULL UNIQUE
);

INSERT INTO category VALUES(1, 'Café');
INSERT INTO category VALUES(2, 'Bar');
INSERT INTO category VALUES(3, 'Sobremesa');
INSERT INTO category VALUES(4, 'Cozinha Portuguesa');
INSERT INTO category VALUES(5, 'Cozinha Italiana');
INSERT INTO category VALUES(6, 'Cozinha Chinesa');
INSERT INTO category VALUES(7, 'Cozinha Japonesa');
INSERT INTO category VALUES(8, 'Marisqueira');
INSERT INTO category VALUES(9, 'Hamburgaria');
INSERT INTO category VALUES(10, 'Fast-Food');
INSERT INTO category VALUES(11, 'Pizzaria');


CREATE TABLE establishment (
	id SERIAL PRIMARY KEY,
	name VARCHAR(64) NOT NULL,
	category VARCHAR(32) REFERENCES category (category)
		ON DELETE SET NULL ON UPDATE CASCADE,
	address VARCHAR(64),
	zone VARCHAR(32),
	city VARCHAR(32),
	email VARCHAR(64),
	contact VARCHAR(16),
	delivery BOOLEAN DEFAULT FALSE,
	avg_price INTEGER,
	schedule1 VARCHAR(64),
	schedule2 VARCHAR(64),
	rating NUMERIC(3,2)
);

CREATE TABLE credential (
	id SERIAL PRIMARY KEY,
	username VARCHAR(32) NOT NULL UNIQUE,
	password VARCHAR(64) NOT NULL,
	role_id INTEGER REFERENCES role 
		ON UPDATE CASCADE NOT NULL,
	users_id INTEGER REFERENCES users
		ON DELETE CASCADE ON UPDATE CASCADE,
	establishment_id INTEGER REFERENCES establishment
		ON DELETE CASCADE ON UPDATE CASCADE,
	tmp_establishment_id INTEGER REFERENCES establishment_tmp
		ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO credential VALUES (DEFAULT, 'admin', 'admin', 3, null, null);

CREATE TABLE comment (
	id SERIAL PRIMARY KEY,
	establishment_id INTEGER REFERENCES establishment 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	commenter_id INTEGER REFERENCES users 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	time_posted TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	rating NUMERIC(1,0),
	comment VARCHAR(256)
);

CREATE TABLE comment_answer (
	id SERIAL PRIMARY KEY,
	comment_id INTEGER REFERENCES comment 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL UNIQUE,
	answer VARCHAR(256),
	time_posted TIMESTAMP DEFAULT CURRENT_TIMESTAMP
); 

CREATE TABLE meal (
	id SERIAL PRIMARY KEY,
	meal VARCHAR(32) NOT NULL UNIQUE,
	price NUMERIC(6,2) NOT NULL,
	establishment_id INTEGER REFERENCES establishment 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);

CREATE TABLE orders (
	id SERIAL PRIMARY KEY,
	users_id INTEGER REFERENCES users 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	state VARCHAR(64)
);

CREATE TABLE orders_meal (
	id SERIAL PRIMARY KEY,
	meal VARCHAR(32) REFERENCES meal (meal) 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	orders_id INTEGER REFERENCES orders 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	quantity INTEGER NOT NULL,
	state VARCHAR(64)
);

CREATE TABLE establishment_image (
	id SERIAL PRIMARY KEY,
	establishment_id INTEGER REFERENCES establishment
		ON DELETE SET NULL ON UPDATE CASCADE,
	extension VARCHAR(8) NOT NULL,
	profile BOOLEAN NOT NULL
);

CREATE TABLE featured (
	id SERIAL PRIMARY KEY,
	meal_id INTEGER REFERENCES meal 
		ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	duration NUMERIC(3,0) NOT NULL
);

CREATE TABLE report_type (
	type VARCHAR(16) PRIMARY KEY
);

INSERT INTO report_type VALUES ('bad_comment');
INSERT INTO report_type VALUES ('bad_info');

CREATE TABLE report (
	id SERIAL PRIMARY KEY,
	type VARCHAR(16) REFERENCES report_type
		ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
	report VARCHAR(256) NOT NULL,
	comment_id INTEGER REFERENCES comment
		ON UPDATE CASCADE ON DELETE CASCADE,
	establishment_id INTEGER REFERENCES establishment
		ON UPDATE CASCADE ON DELETE CASCADE,
	reporter_id INTEGER REFERENCES users
);

CREATE TABLE establishment_tmp (
	id SERIAL PRIMARY KEY,
	name VARCHAR(64),
	category VARCHAR(32) REFERENCES category (category)
		ON DELETE SET NULL ON UPDATE CASCADE,
	address VARCHAR(64),
	zone VARCHAR(32),
	city VARCHAR(32),
	email VARCHAR(64),
	contact VARCHAR(16),
	delivery BOOLEAN DEFAULT FALSE,
	avg_price INTEGER,
	schedule1 VARCHAR(64),
	schedule2 VARCHAR(64),
	rating NUMERIC(3,2)
);

CREATE TABLE promotion (
	id SERIAL PRIMARY KEY,
	title VARCHAR(64),
	description VARCHAR(256),
	code VARCHAR(64),
	good_until DATE
);