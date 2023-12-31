CREATE TABLE IF NOT EXISTS person (
	id serial PRIMARY KEY,
	first_name varchar(255),
	last_name varchar(255),
	middle_name varchar(255),
	birth_date date,
	passport_serial integer,
	passport_number integer
	);

INSERT INTO person (first_name, last_name, middle_name,
	birth_date, passport_serial, passport_number)
	VALUES
	('Ivan', 'Ivanov', 'Ivanovich',
	 '1988-01-01', 6788, 2904456
	);

SELECT * FROM person;

UPDATE person
	SET last_name = 'Petrov'
	WHERE last_name = 'Ivanov';

DELETE FROM person
    WHERE first_name = 'Ivan'
    AND last_name = 'Petrov';
