insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, email, password, enabled, authority_id)
values ('root', 'admin@admin.ru', '$2a$10$kTqGA646qzfySH6VoeVmO.mUKnliWeguseDTXaCSQjm2dInYv0LQ6',
        true, (select id from authorities where authority = 'ROLE_ADMIN'));