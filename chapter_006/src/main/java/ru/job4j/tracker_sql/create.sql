CREATE DATABASE tracker;

CREATE TABLE roles(
	role_id serial primary key,
	role_desc character varying (100)
	);

CREATE TABLE rules(
	rule_id serial primary key,
	rule_desc character varying (100)
);

CREATE TABLE rules_for_roles(
	comb_id serial primary key,
	role_id integer references Roles(role_id),
	rule_id integer references Rules(rule_id)
);

CREATE TABLE users(
	user_id serial primary key,
	user_name character varying (100),
	user_role integer references Roles(role_id)
);

CREATE TABLE categories(
	cat_id serial primary key,
	cat_desc character varying (100)
);

CREATE TABLE states(
	state_id serial primary key,
	state_desc character varying (100)
);

CREATE TABLE items(
	item_id serial primary key,
	i_user integer references Users(user_id),
	i_state integer references States(state_id),
	i_category integer references Categories(cat_id)
);

CREATE TABLE attaches(
	attach_id serial primary key,
	item_id integer references Items(item_id),
	attach_desc text
);

CREATE TABLE commentaries(
	com_id serial primary key,
	item_id integer references Items(item_id),
	com_text text
);

INSERT INTO roles (role_desc)
VALUES ('Tester'), ('Developer'), ('PM');

INSERT INTO rules (rule_desc)
VALUES ('Create'), ('Read'), ('Write');

INSERT INTO rules_for_roles (role_id, rule_id)
VALUES (1, 2), (1, 3), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3);

INSERT INTO users (user_name, user_role)
VALUES ('User1', 1), ('User2', 2), ('User3', 3);

INSERT INTO categories (cat_desc)
VALUES ('Task'), ('Bug');

INSERT INTO states (state_desc)
VALUES ('Open'), ('In Progress'), ('Closed'), ('Suspended');

INSERT INTO items (i_user, i_state, i_category)
VALUES (2, 2, 1), (3, 4, 1), (1, 2, 1);

INSERT INTO commentaries (item_id, com_text)
VALUES (1, 'doing right now'), (1, 'almos done'), (2, 'backlogged'), (3, 'rechecking');

INSERT INTO attaches (item_id, attach_desc)
VALUES (1, 'screen.png'), (3, 'bug.png'), (3, 'logcat.txt');