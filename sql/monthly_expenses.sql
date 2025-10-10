use expense_records;
create table monthly_expenses
(
    id            varchar(255)                       not null comment '月表唯一ID'
        primary key,
    year          int                                not null comment '年份（关联年表）',
    month         int                                not null comment '月份（1-12）',
    monthly_total decimal(12, 2)                     not null comment '当月花销合计',
    create_by     varchar(255)                       null comment '创建人',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_by     varchar(255)                       null comment '更新人',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_year_month
        unique (year, month)
)
    comment '用户月度花销汇总表';

INSERT INTO expense_records.monthly_expenses (id, year, month, monthly_total, create_by, create_time, update_by, update_time) VALUES ('1973065759657893890', 2025, 10, 602.29, '1', '2025-10-01 00:41:49', '1', '2025-10-07 13:20:55');
INSERT INTO expense_records.monthly_expenses (id, year, month, monthly_total, create_by, create_time, update_by, update_time) VALUES ('46', 2025, 8, 119.27, '1', '2025-09-27 22:10:20', '1', '2025-09-29 20:32:49');
INSERT INTO expense_records.monthly_expenses (id, year, month, monthly_total, create_by, create_time, update_by, update_time) VALUES ('47', 2025, 9, 4062.72, '1', '2025-09-27 22:10:20', '1', '2025-09-29 20:32:49');
