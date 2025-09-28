create table yearly_expenses
(
    id           varchar(255)                       not null comment '主键'
        primary key,
    year         int                                not null comment '年份（如2025）',
    yearly_total decimal(12, 2)                     null comment '当年花销合计',
    create_by    varchar(255)                       null comment '创建人',
    create_time  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_by    varchar(255)                       null comment '更新人',
    update_time  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户年度花销汇总表';

INSERT INTO expense_records.yearly_expenses (id, year, yearly_total, create_by, create_time, update_by, update_time) VALUES ('1', 2025, 2036.18, null, '2025-09-27 22:10:20', null, '2025-09-27 22:10:20');
