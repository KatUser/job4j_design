create table customer
(
    id    serial primary key,
    "name"  varchar(50),
	amount_spent int
);

insert into customer
    ("name", amount_spent)
    values
    ('Petya', 10000), ('Galya', 500000), ('Vasya', 0);

--- transaction 1;
begin transaction isolation level serializable;
update customer set amount_spent = 1000 where name='Vasya';

--- transaction 2;
begin transaction isolation level serializable;
update customer set amount_spent=9000 where name='Galya';
commit; -- OK

--- transaction 1;
commit; --ERROR