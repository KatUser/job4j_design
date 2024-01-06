begin;
declare
cur_p scroll cursor for
select * from products;

--move
move forward all from cur_p; -- в конец табл

move backward 1 from cur_p; -- на 20

move backward 5 from cur_p; --на 15

move backward 8 from cur_p; --на 7

move backward 5 from cur_p; -- на 2

move prior from cur_p; --на 1

close cur_p;

commit transaction;

--fetch
fetch last from cur_p; -- на 20

fetch backward 5 from cur_p; -- на 15

fetch backward 8 from cur_p; -- На 7

fetch backward 5 from cur_p; -- на 2

fetch prior from cur_p; -- на 1

close cur_p;

commit transaction;
