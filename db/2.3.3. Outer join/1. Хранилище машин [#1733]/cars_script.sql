--- 1. Хранилище машин [#1733]
create table car_bodies (
	id serial primary key,
	"name" varchar(255)
);
create table car_engines (
	id serial primary key,
	"name" varchar(255)
);
create table car_transmissions (
	id serial primary key,
	"name" varchar(255)
);
create table cars (
	id serial primary key,
	"name" varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);
insert into car_bodies
	("name")
	values ('седан'), ('лимузин'), ('пикап'),
	('хэтчбэк'), ('универсал'), ('лифтбек'), ('минивен'),
	('купе'), ('кабриолет'), ('родстер'), ('тарга');

insert into car_engines
	("name")
	values ('бензиновый'), ('дизельный'), ('газовый'),
	('гибридный'), ('кукурузный');

insert into car_transmissions
	("name")
	values ('механическая'), ('автоматическая'), ('роботизированная'),
	('вариативная');

insert into cars
	("name", body_id, engine_id, transmission_id)
	values
	('тойта', 1, 1, 2),
	('ниссан', 5, 1, 2),
	('мазда', 7, 5, 3),
	('жигули', 1, 1, null),
	('киа', 6, 3, 2),
	('корыто', null, 5, 1);

--1. Вывести список всех машин и все привязанные к ним детали.  id, car_name, body_name, engine_name, transmission_name

select "c".id,"c".name as "car_name",
    cb.name as "body_name",
    ce.name as "engine_name",
    ct.name as "transmission_name" from cars "c"
    left join car_bodies cb on "c".body_id = cb.id
    left join car_engines ce on "c".engine_id = ce.id
    left join car_transmissions ct on "c".transmission_id = ct.id;

--2. Вывести кузова, которые не используются НИ в одной машине.
select cb.name
    from car_bodies cb
    left join cars "c"
    on cb.id = "c".body_id
    where "c".body_id is null;

select cb.name from car_bodies cb
where not exists (
	select body_id from cars "c"
				 where "c".body_id = cb.id);

-- 3. Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
select ce.name
    from car_engines ce
    left join cars "c"
    on ce.id = "c".engine_id
    where "c".engine_id is null;

-- 4. Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
select ct.name
    from car_transmissions ct
    left join cars "c"
    on ct.id = "c".transmission_id
    where "c".transmission_id is null;
