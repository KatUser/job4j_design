CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers
(first_name, last_name, age, country)
values
('Tom', 'Poll', 55, 'Australia'),
('Helen', 'Smith', 20, 'USA'),
('Ivan', 'Ivanov', 21, 'Russia'),
('Ann', 'Boleyn', 35, 'Ireland'),
('Ogly', 'Bagahamon', 24, 'Uzbekistan'),
('Jane', 'Air', 40, 'England');

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders
(amount, customer_id)
values (10, 1), (5, 2), (7, 3);

--- запрос, который вернет список клиентов, возраст которых является минимальным.
select first_name, last_name
    from customers
    where age < (select avg(age) from customers);

-- запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа.
select "id" from customers
    where "id" not in (select customer_id from orders);