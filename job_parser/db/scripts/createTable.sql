CREATE TABLE IF NOT EXISTS vacancies (
id SERIAL PRIMARY KEY,
name TEXT UNIQUE,
descr TEXT,
link TEXT
);