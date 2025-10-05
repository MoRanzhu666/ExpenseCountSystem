use expense_records;
create table users
(
    id          varchar(255) not null
        primary key,
    name        varchar(255) null,
    role        varchar(255) null,
    create_by   varchar(255) null,
    create_time datetime     null,
    update_by   varchar(255) null,
    update_time datetime     null,
    password    varchar(255) not null comment '密码'
);

INSERT INTO expense_records.users (id, name, role, create_by, create_time, update_by, update_time, password) VALUES ('1', 'Lyrics', 'SYSTEM_MANAGER', '1', '2025-09-27 22:11:00', '1', '2025-09-27 22:11:06', 'Lyrics');
