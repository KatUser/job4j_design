-- 1. Создать таблицы и заполнить их начальными данными
create table departments (
	id serial primary key,
	name varchar(255)
	);

create table employees (
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
	);

insert into departments
	("name")
	values ('Engineering'), ('Logistics'),
	('Human resources'), ('Accounting'),
	('Loiterers');

insert into employees
	("name", departments_id)
	values
	('Engineer_1', 1), ('Engineer_2', 1), ('Engineer_3', 1),
	('Logistician_1', 2), ('Logistician_2', 2),
	('HR_1', 3), ('HR_2', 3),
	('Accountant_1', 4), ('Accountant_2', 4),
	('Somebody', null);

--2.1. left outer join
select * from employees e
    left join departments d
    on e.departments_id = d.id;

--2.2. right outer join
select * from departments d
    right join employees e
    on d.id = e.departments_id;

select count(e."name"), d."name" from departments d
    right join employees e
    on d.id = e.departments_id
    where d."name" is not null
    group by d."name";

--2.3. full join
select * from employees e
    full join departments d
    on e.departments_id = d.id;

--2.4. cross join
select * from employees
    cross join departments;

--3. Используя left join найти департаменты, у которых нет работников
select d."name" from departments d
    left join employees e
    on d.id = e.departments_id
    group by d.id
    having count(e.departments_id) = 0;

--4.  Используя left и right join написать запросы,
-- которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select * from employees e
    left join departments d
    on e.departments_id = d.id
    where d."name" is not null;

select * from employees e
    right join departments d
    on e.departments_id = d.id
    where e."name" is not null;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens (
	id serial primary key,
	"name" varchar(255),
	gender varchar(255)
);

insert into teens
	("name", gender)
	values
	('Female_1', 'Female'),('Male_1', 'Male'),
	('Female_2', 'Female'),('Male_2', 'Male');

select t1."name" as t1, t2."name" as t2
	from teens t1
	cross join teens t2
	where t1.gender != t2.gender;
