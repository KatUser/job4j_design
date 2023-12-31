create table fauna
(
    id             serial primary key,
    "name"         text,
    avg_age        int,
    discovery_date date
);

insert into fauna
	("name", avg_age, discovery_date)
	values
	('Six-eyed guitarfish', 201, '2020-07-15'),
	('Mata-mata turtle', 90000, '2019-09-11'),
	('Fairy possum', 10000, '2010-01-31'),
	('Spotted dolphin', 15000, '2001-05-13'),
	('Fur frog', 105, '2014-08-17'),
	('Nemo fish', 280, '2005-12-21'),
	('Silver toad', 50, null),
	('Pink salmon', 178, '1945-04-30'),
	('Purple tiger', 19000, '1900-01-02');

--1. Извлечение данных, у которых имя name содержит подстроку fish
select * from fauna
	where name like '%fish%';

--2. Извлечение данных, у которых сред. продолжительность жизни находится в диапазоне 10 000 и 21 000
select * from fauna
	where avg_age >= 10000
	and avg_age <= 21000;
select * from fauna
	where avg_age between 10000
	and 21000;

--3. Извлечение данных, у которых дата открытия не известна (null)
select * from fauna
	where discovery_date is null;

--4. Извлечение данных видов, у которых дата открытия раньше 1950 года
select * from fauna
	where discovery_date < '1950-01-01';