create table users
(
    id          varchar(255) not null
        primary key,
    name        varchar(255) null,
    password    varchar(255) not null comment '密码',
    role        varchar(255) null,
    create_by   varchar(255) null,
    create_time datetime     null,
    update_by   varchar(255) null,
    update_time datetime     null
);

INSERT INTO expense_records.users (id, name, password, role, create_by, create_time, update_by, update_time) VALUES ('1', 'Lyrics', 'e10adc3949ba59abbe56e057f20f883e', 'SYSTEM_MANAGER', '1', '2025-09-27 22:11:00', '1', '2025-10-07 09:27:39');
