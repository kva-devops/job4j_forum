create table posts (
    id serial primary key,
    name varchar (2000),
    description text,
    created timestamp without time zone not null default now(),
    user_id int references users(id)
);

create table users (
    id serial primary key,
    name varchar (50),
    email varchar (50),
    password varchar (50)
)