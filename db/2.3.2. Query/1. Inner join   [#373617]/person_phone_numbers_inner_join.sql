create table if not exists phone_numbers (
	id serial primary key,
	phone_number varchar(255),
	"comment" text
);

create table if not exists person (
	id serial primary key,
	phone_num_id int references phone_numbers(id) unique,
	first_name varchar(255),
	last_name varchar(255),
	middle_name varchar(255),
	birth_date date
);

select "p".last_name as "Фамилия",
	"p".first_name as "Имя",
	"p".middle_name as "Отчество",
	ph.phone_number as "Номер телефона"
	from person as "p" 
	inner join phone_numbers as ph
	on "p".phone_num_id = ph.id;

select ph."comment" as "Комментарий к номеру тел",
	"p".last_name as "Фамилия"
	from phone_numbers as ph
	inner join person as "p"
	on ph.id = "p".phone_num_id
	where ph."comment" is not null;

select "p".birth_date as "Дата рождения", 
	"p".first_name as "Имя",
	"p".middle_name as "Отчество",
	ph.phone_number as "Тел"
	from person as "p"
	inner join phone_numbers as ph
	on "p".phone_num_id = ph.id
	where "p".middle_name like '%vna';
