create table posts (
  id serial primary key,
  name varchar(2000),
  description text,
  created timestamp without time zone not null default now()
);

CREATE TABLE IF NOT EXISTS users
(
    id serial primary key,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);