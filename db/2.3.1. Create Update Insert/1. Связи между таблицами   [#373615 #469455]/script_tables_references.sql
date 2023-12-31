-- many-to-one: position  <-> department
create table if not exists department (
    id serial primary key,
    name varchar(255)
);

create table if not exists position (
    id serial primary key,
    name varchar(255),
    department_id int references department(id)
);

insert into department
    (name)
    values ('Engineering'), ('Accounting'), ('Human resourses');

insert into "position"
    (name, department_id)
    values ('Developer', 1), ('Systems analyst', 1), ('Quality assurance engineer', 1);

select * from department
    where id in (select department_id from "position");

--one-to-one: employee <-> ID badge
create table if not exists id_badge (
    id serial primary key,
	endorsements text
);

create table if not exists employee (
    id serial primary key,
    full_name varchar(255),
    birth_date date,
    position_id int references "position"(id),
    id_badge int references id_badge (id) unique
    );

insert into id_badge
	(endorsements)
	values ('Lazy, but creative'),
		('Nice, but stupid'),
		('Not experienced, but promising');

insert into employee
	(full_name, birth_date, position_id, id_badge)
	values ('John Doe', '1988-12-12', 1, 1),
	('Tom Cat', '1990-01-25', 3, 2),
	('Mickey Mouse', '1982-09-04', 1, 3);

select * from employee
    where id_badge
    in (select id from id_badge where id = 1);

-- many-to-many employee <-> task;
create table if not exists task (
    id serial primary key,
    task_name varchar(255),
	task_description text
);

insert into task (task_name, task_description)
values ('Task-1', 'Need to add a new endpoint...'),
		('Task-2', 'Add a new parameter to ...' );

create table if not exists employee_task (
    id serial primary key,
    employee_id int references employee (id),
    task_id int references task (id)
);

insert into employee_task (employee_id, task_id)
    values (1, 2), (2, 1) , (2, 2), (2, 1), (3, 2);

select * from employee_task
    where employee_id
    in (select id from employee)
    and task_id in (select id from task);




