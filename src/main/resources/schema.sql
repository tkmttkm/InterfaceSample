CREATE TABLE animal_kinds (
	id INT PRIMARY KEY,
	name VARCHAR(50),
	feature VARCHAR(50)
);

CREATE TABLE animal_name (
	id INT,
	kinds_id INT,
	name VARCHAR(50),
	PRIMARY KEY (id, kinds_id)
);