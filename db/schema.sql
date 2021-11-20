create table authorities (
    id serial primary key,
    authority varchar (50) not null unique
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

create table users (
    id serial primary key,
    name varchar (50),
    email varchar (50) not null unique,
    password varchar (100) not null,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);

insert into users (name, email, password, enabled, authority_id)
values ('root', 'admin@admin.ru', '$2a$10$kTqGA646qzfySH6VoeVmO.mUKnliWeguseDTXaCSQjm2dInYv0LQ6',
true, (select id from authorities where authority = 'ROLE_ADMIN'));

create table posts (
    id serial primary key,
    name varchar (2000),
    description text,
    created timestamp without time zone not null default now(),
    user_id int references users(id)
);



