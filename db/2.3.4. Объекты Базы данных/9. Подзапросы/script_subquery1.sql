CREATE TABLE companies
(
    id   int primary key,
    city text
);

CREATE TABLE goods
(
    id         int primary key,
    name       text,
    company_id int references companies (id),
    price      int
);

CREATE TABLE sales_managers
(
    id          int primary key,
    last_name   text,
    first_name  text,
    company_id  int references companies (id),
    manager_fee int
);

CREATE TABLE managers
(
    id         int primary key,
    company_id int references companies (id)
);

CREATE TABLE managers
(
    id         int primary key,
    company_id int references companies (id)
);

INSERT INTO companies
VALUES (1, 'Москва'),
       (2, 'Нью-Йорк'),
       (3, 'Мюнхен');

INSERT INTO goods
VALUES (1, 'Небольшая квартира', 3, 5000),
       (2, 'Квартира в центре', 1, 4500),
       (3, 'Квартира у метро', 1, 3200),
       (4, 'Лофт', 2, 6700),
       (5, 'Загородный дом', 2, 9800);

INSERT INTO sales_managers
VALUES (1, 'Доу', 'Джон', 2, 2250),
       (2, 'Грубер', 'Ганс', 3, 3120),
       (3, 'Смит', 'Сара', 2, 1640),
       (4, 'Иванов', 'Иван', 1, 4500),
       (5, 'Купер', 'Грета', 3, 2130);

INSERT INTO managers
VALUES (1, 2),
       (2, 3),
       (4, 1);

select * from sales_managers
    where manager_fee >
    (select avg(manager_fee) from sales_managers);

select "name" as real_estate, price,
    (select avg(price) from goods)
    as avg_price from goods;

select avg(manager_fee)
    from sales_managers
    where sales_managers.id not in
    (select managers.id from managers);


select city,
    (select count(*)
    from goods as g
    where c.id = g.company_id) as "total_goods"
    from companies c;

select c.city, count(g.name) as total_goods
    from companies c
    join goods g on c.id =  g.company_id
    group by c.city;

select last_name, first_name, manager_fee
    from sales_managers sm1
    where sm1.manager_fee >=
    (select avg(manager_fee)
    from sales_managers sm2
    where sm2.company_id = sm1.company_id);

select company_id, avg(price) as average_price
    from goods
    group by company_id
    having avg(price) >
    (select max(price) from goods) / 2;