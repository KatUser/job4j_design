create table customer
(
    id   serial primary key,
    full_name varchar(50)
);

create table brand
(
	id serial primary key,
	"name" varchar (255)
);

create table product
(
	id serial primary key,
	brand_id int references brand(id),
	"name" varchar(255)
);

create table "order"
(
	id serial primary key,
	paid boolean,
	customer_id int references customer(id),
	product_id int references product(id)
);

insert into customer
	(full_name)
	values ('Erl Grey'), ('Jasmine Green'), ('Milk Oolong');
insert into brand
	("name")
	values ('JVC'), ('Sony'), ('Made in China');
insert into product
	(brand_id, "name")
	values
	(1, 'Oven'), (2, 'Laundry machine'), (3, 'Coffee machine'),
	(3, 'Hoover'), (3, 'Lamp');
insert into "order"
	(paid, customer_id, product_id)
	values
	(false, 1, 1), (true, 2, 2), (true, 3, 3), (true, 3, 4), (true, 3, 5);

create view show_2_custs_with_max_amount_of_paid_orders_and_orders_amount
as
select "c".full_name as "Имя клиента", count(o.id) as "Кол-во заказов"
	from "order" o
	inner join customer "c" on o.customer_id = "c".id
	inner join product "p" on o.product_id = "p".id
	inner join brand b on b.id = "p".brand_id
	where paid = true
	group by "c".full_name
	order by count(*) limit 2;

select * from show_2_custs_with_max_amount_of_paid_orders_and_orders_amount;

alter view show_2_custs_with_max_amount_of_paid_orders_and_orders_amount
rename to show_2_custs_name_with_max_amount_of_paid_orders_and_orders;

select * from show_2_custs_name_with_max_amount_of_paid_orders_and_orders;

drop view show_2_custs_name_with_max_amount_of_paid_orders_and_orders;