create table passport(
    id serial primary key,
    seria int,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    passport_id int references passport(id) unique
);

insert into passport(seria, number) values (1111, 123456);

insert into passport(seria, number) values (1112, 123457);
insert into passport(seria, number) values (1113, 123458);

insert into people(name, passport_id) values ('Ivan', 1);
insert into people(name, passport_id) values ('Boris', 2);
insert into people(name, passport_id) values ('Petr', 3);
insert into people(name) values ('Vasya');
insert into people(name) values ('Anya');
delete from people where passport_id is null;
select * from passport;
select * from people;

select * from people inner join
passport on people.passport_id = passport.id;
select * from people join passport 
on people.passport_id = passport.id;

select pp.name, p.seria, p.number
from people as pp 
inner join passport as p 
on pp.passport_id = p.id;

select pp.name as "Имя", 
p.seria as "Серия паспорта", 
p.number as "Номер паспорта"
from people as pp 
inner join passport as p 
on pp.passport_id = p.id;

select pp.name as "Имя владельца", 
p.seria "Серия", 
p.number "Номер"
from people as pp
inner join passport as p
on pp.passport_id = p.id;














