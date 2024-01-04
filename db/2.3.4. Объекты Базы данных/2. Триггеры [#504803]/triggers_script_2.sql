--1. Триггер должен срабатывать после вставки данных, для любого товара,  statement уровень;
create
or replace function after_insert_func()
    returns trigger as
$$
    begin
        update products
        set price = price + price * 0.5
        where id = (select id from inserted);
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger after_insert_func_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure after_insert_func();

drop trigger after_insert_func_trigger on products;

-- 2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар, row уровень;
create
or replace function before_insert_func()
    returns trigger as
$$
    begin
		new.price = new.price * 2;
		  return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger before_insert_func_trigger
    before insert
    on products
    for each row
    execute procedure before_insert_func();

drop trigger before_insert_func_trigger on products;

-- 3. Триггер на row уровне, который  после вставки в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price;
create table history_of_price
(
    id    serial primary key,
    "name"  varchar(50),
    price integer,
    "date"  timestamp
);

create
or replace function log_history_of_price()
    returns trigger as
$$
BEGIN
	insert into history_of_price("name", price, "date")
	values(NEW."name", NEW.price, now());
	RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger log_price
	after insert on products
	for each row
	execute procedure log_history_of_price();

drop trigger log_price on products;
