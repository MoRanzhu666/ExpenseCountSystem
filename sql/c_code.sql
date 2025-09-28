create table c_code
(
    id          varchar(255) not null
        primary key,
    category    varchar(255) null,
    code        varchar(255) null,
    `describe`  varchar(255) null,
    create_by   varchar(255) null,
    create_time datetime     null,
    update_by   varchar(255) null,
    update_time datetime     null
);

INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1', 'ROLE', 'SYSTEM_MANAGER', '系统管理员', '1', '2025-09-27 23:44:59', '1', '2025-09-27 23:45:03');
