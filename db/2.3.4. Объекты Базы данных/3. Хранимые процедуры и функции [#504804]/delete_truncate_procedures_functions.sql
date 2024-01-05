-- Добавить процедуру и функцию, которая будет удалять записи.

-- procedures
create
or replace procedure delete_data_from_products()
language 'plpgsql'
as
$$
	begin
	    truncate products;
	    alter sequence products_id_seq restart with 1;
	end;
$$;

call delete_data_from_products()();

create
or replace procedure delete_row_by_id(d_id integer)
language 'plpgsql'
as
$$
	begin
	    delete from products
	    where id = d_id;
	end;
$$;

call  delete_row_by_id(1);

-- functions

create
or replace function f_delete_table_products()
returns void
language 'plpgsql'
as
$$
    begin
		truncate products;
		alter sequence products_id_seq restart with 1;
	end;
$$;

select f_delete_table_products();


create
or replace function f_delete_row_by_product_name(d_prod_name varchar)
returns integer
language 'plpgsql'
as
$$
	declare
		result integer;
	begin
		if (select count(*) from products
		    where "name" = d_prod_name) < 2
		then
		    select into result count(d_prod_name)
		    from products where "name" = d_prod_name;
		    delete from products where "name" = d_prod_name;
		else
		    select into result count(d_prod_name)
		    from products where "name" = d_prod_name;
		end if;
		return result;
	end;
$$;

select f_delete_row_by_product_name('product_2');

