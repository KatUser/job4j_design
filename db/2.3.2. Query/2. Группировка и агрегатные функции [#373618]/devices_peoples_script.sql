create table devices
(
    id    serial primary key,
    "name"  varchar(255),
    price float
);
select * from devices;

insert into devices
("name", price)
values
('Samsung', 25000.99), ('Xiaomi', 35999.00), ('iPhoneSE', 99000.99), ('RealMe', 49555.99);

create table peoples
(
    id   serial primary key,
    "name" varchar(255)
);

insert into peoples
("name")
values
('Petya'), ('Galya'), ('Vasya'), ('Lera');

 create table devices_peoples
(
    id        serial primary key,
    device_id int references devices (id),
    peoples_id int references peoples (id)
);

insert into devices_peoples
(device_id, peoples_id)
values
(1, 1), (2, 2), (3, 3), (4, 4), (1, 4), (2, 3), (3, 4), (4, 4);

-- Используя агрегатные функции вывести среднюю цену устройств.
select avg(price) as "Средняя цена устройств"
    from devices;

-- Используя группировку вывести для каждого человека среднюю цену его устройств.
select "p"."name" as "Имя",
    avg(d.price) as "Средняя цена всех устройств"
    from peoples as "p"
    inner join devices_peoples as dp
    on "p".id = dp.peoples_id
    inner join devices as d
    on d.id = dp.device_id
    group by "p".name;

-- Используя группировку вывести для каждого человека среднюю цену его устройств > 5000.
select "p"."name" as "Имя",
    avg(d.price) as "Средняя цена всех устройств"
    from peoples as "p"
    inner join devices_peoples as dp
    on "p".id = dp.peoples_id
    inner join devices as d
    on d.id = dp.device_id
    group by "p".name
    having avg(d.price) > 5000;
