--CREATE DATABASE car_catalog;

CREATE TABLE transmissions (id serial PRIMARY KEY, name varchar(100));
CREATE TABLE bodies (id serial PRIMARY KEY, name varchar(100));
CREATE TABLE engines (id serial PRIMARY KEY, name varchar(100));

CREATE TABLE cars (id serial PRIMARY KEY,
transmission integer UNIQUE REFERENCES transmissions(id),
engine integer UNIQUE REFERENCES engines (id),
body integer UNIQUE REFERENCES bodies (id)
);

INSERT INTO transmissions (name)
VALUES ('TR1'), ('TR2'), ('TR3'), ('TR4'), ('TR5'), ('TR6'), ('TR7');

INSERT INTO engines (name)
VALUES ('ENG1'), ('ENG2'), ('ENG3'), ('ENG4'), ('ENG5'), ('ENG6'), ('ENG7');

INSERT INTO bodies (name)
VALUES ('BD1'), ('BD2'), ('BD3'), ('BD4'), ('BD5'), ('BD6'), ('BD7');

INSERT INTO cars (transmission, engine, body)
VALUES (1, 2, 3), (2, 1, 2), (3, 3, 1), (4, 4, 4), (7, 7, 7);
