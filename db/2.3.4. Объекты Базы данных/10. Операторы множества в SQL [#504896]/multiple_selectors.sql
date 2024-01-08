CREATE TABLE customer
(
    first_name text,
    last_name  text
);

CREATE TABLE employee
(
    first_name text,
    last_name  text
);

INSERT INTO customer
VALUES ('Иван', 'Иванов'),
       ('Петр', 'Сергеев'),
       ('Ирина', 'Бросова'),
       ('Анна', 'Опушкина'),
       ('Потап', 'Потапов');

INSERT INTO employee
VALUES ('Кристина', 'Позова'),
       ('Михаил', 'Кругов'),
       ('Анна', 'Опушкина'),
       ('Иван', 'Иванов'),
       ('Сергей', 'Петров');

select first_name, last_name
from customer
union
select first_name, last_name
from employee;

select first_name, last_name
from customer
union
select first_name, last_name
from employee
order by first_name, last_name;

select first_name, last_name
from customer
union all
select first_name, last_name
from employee
order by first_name, last_name;

select first_name, last_name
from customer
except
select first_name, last_name
from employee
order by first_name, last_name;

select first_name, last_name
from customer
intersect
select first_name, last_name
from employee
order by first_name, last_name;

CREATE TABLE referrer
(
    first_name text,
    last_name  text
);

INSERT INTO referrer
VALUES ('Евгений', 'Онегин'),
       ('Петр', 'Сергеев'),
       ('Александр', 'Ожегов'),
       ('Анна', 'Опушкина'),
       ('Михаил', 'Кругов');

SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee
UNION
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
UNION ALL
SELECT first_name, last_name
FROM employee
EXCEPT
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
UNION ALL
(SELECT first_name, last_name
 FROM employee
 EXCEPT
 SELECT first_name, last_name
 FROM referrer)
ORDER BY first_name, last_name;
