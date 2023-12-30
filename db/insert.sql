insert into roles
	(role_name)
	values
	('Administrator'), ('Seller'), ('Buyer');
	select * from roles;

insert into "rules"
	(rule_name)
	values
	('Block a seller'), ('Block a buyer'), ('Edit a flypage'),
	('Delete a flypage'), ('Comment a flypage'), ('Create a flypage'),
	('Set an order'), ('Cancel an order');
	select * from "rules";

insert into roles_rules
	(role_id, rule_id)
	values
	(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8),
	(2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 8),
	(3, 5), (3, 7), (3, 8);

insert into users
	(user_role, user_login)
	values
	(1, 'Admin_1'), (1, 'Admin_2'),
	(2, 'Moder_1'),
	(3, 'User_Cat'), (3, 'Buyer_1');

insert into states
	(state_name)
	values
	('Draft'), ('Awaiting payment'), ('Fully payed'),
	('Partially payed'), ('Processing'), ('Canceled'),
	('Refunded'), ('Finalized'), ('Overdue');

insert into categories
	(category_name)
	values
	('Animals'), ('Households'), ('Books'),
	('Food'), ('Electronics');

insert into items
	(user_id, category_id, item_state, item_name)
	values
	(4, 3, 9, 'Alice in Wonderland'),
	(4, 1, 3, 'Whiskas'),
	(5, 2, 7, 'Armchair' ),
	(4, 4, 4, 'Marshmallow');

insert into "comments"
	(item_id, comment_text)
	values
	(1, 'The buyer can receive a discount'),
	(2, 'Call the buyer at +79138888899'),
	(2, 'Should be shipped ASAP!'),
	(3, 'Call them'),
	(3, 'Block this buyer!');

insert into attachments
	(item_id, attachment_name)
	values
	(1, 'photoid/12/2023/jhdjhddDdqwJwwerrr.jpg'),
	(1, 'photoid/12/2023/ydidifgDpgpaAaa.jpg'),
	(2, 'photoid/11/2023/rkfCjklanjdkVVlsjla.jpg'),
	(4, 'photoid/11/2023/uijejflaKSLgjksk.jpg');
