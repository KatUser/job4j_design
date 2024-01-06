create table laptop (
	id serial primary key,
	producer varchar(100),
	diagonal float,
	on_sale boolean
	);

start transaction;

insert into laptop
	(producer, diagonal, on_sale)
	values ('Acer', 17.5, true),
	('Lenovo', 17.8, true),
	('Asus', 16.8, false);

savepoint first_savepoint;

delete from laptop where diagonal < 16.9;

savepoint second_savepoint;

insert into laptop
	(producer, diagonal, on_sale)
	values ('Oppo', 17.0, false);

savepoint third_savepoint;

drop table laptop;

rollback to third_savepoint;

rollback to second_savepoint;

rollback to first_savepoint;

commit transaction;