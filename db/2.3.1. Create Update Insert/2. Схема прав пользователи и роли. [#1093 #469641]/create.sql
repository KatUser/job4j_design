-- roles - rules = many-to-many (у ролей есть права)
create table if not exists roles (
	id serial primary key,
	role_name varchar(255)
	);

create table if not exists "rules" (
    id serial primary key,
    rule_name varchar(255)
	);

create table if not exists roles_rules (
	id serial primary key,
	role_id int references roles(id),
	rule_id int references "rules"(id)
	);

-- users - roles = many-to-one (у пользователя есть роли )
create table if not exists users (
	id serial primary key,
	user_role int references roles(id),
	user_login varchar(255)
	);

-- items - states = many-to-one (у заявки есть состояния)
create table if not exists states (
	id serial primary key,
	state_name varchar(255)
	);

-- items - categories = many-to-one (у заявки есть категории)
create table if not exists categories (
	id serial primary key,
	category_name varchar(255)
	);

-- items - users = many-to-one (у пользователя есть заявки)
create table if not exists items (
	id serial primary key,
	user_id int references users(id),
	category_id int references categories(id),
	item_state int references states(id),
	item_name varchar(255)
	);

-- items - comments = one-to-many (у заявки есть комментарии)
create table if not exists "comments" (
	id serial primary key,
	item_id int references items(id),
	comment_text text
	);

-- items - attachments = one-to-many (у заявки есть приложенные файлы)
create table if not exists attachments (
	id serial primary key,
	item_id int references items(id),
	attachment_name varchar(255)
	);