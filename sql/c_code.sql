use expense_records;

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
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973544860931416065', 'CATEGORY', 'EXPENSE', '消费分类', '1', '2025-10-02 08:25:36', '1', '2025-10-02 08:39:42');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584298256404482', 'EXPENSE', 'SNACKS', '零食', '1', '2025-10-02 11:02:19', '1', '2025-10-02 11:02:19');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584332481925122', 'EXPENSE', 'THREE_MEALS', '三餐', '1', '2025-10-02 11:02:27', '1', '2025-10-02 11:02:27');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584362995486722', 'EXPENSE', 'DRINKING_WATER', '饮水', '1', '2025-10-02 11:02:34', '1', '2025-10-02 11:02:34');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584424936968193', 'EXPENSE', 'ADULT_PRODUCTS', '成人用品', '1', '2025-10-02 11:02:49', '1', '2025-10-02 11:02:49');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584453173022722', 'EXPENSE', 'COMMUTING', '通勤', '1', '2025-10-02 11:02:56', '1', '2025-10-02 11:02:56');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584490196144130', 'EXPENSE', 'SHOPPING', '购物', '1', '2025-10-02 11:03:05', '1', '2025-10-02 11:03:05');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584617409384450', 'EXPENSE', 'PARCEL_DELIVERY', '寄件', '1', '2025-10-02 11:03:35', '1', '2025-10-02 11:03:35');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584671788535810', 'EXPENSE', 'UNEXPENSE', '无消费', '1', '2025-10-02 11:03:48', '1', '2025-10-02 11:03:48');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584731326681090', 'EXPENSE', 'PHONE_CREDIT', '话费', '1', '2025-10-02 11:04:02', '1', '2025-10-02 11:04:02');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1973584764310687746', 'EXPENSE', 'GAMING', '游戏', '1', '2025-10-02 11:04:10', '1', '2025-10-02 11:04:10');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1974316941470134274', 'EXPENSE', 'MEDICINE', '药品', '1', '2025-10-04 11:33:35', '1', '2025-10-04 11:33:35');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('1974405078426906626', 'EXPENSE', 'OTHER', '其他', '1', '2025-10-04 17:23:48', '1', '2025-10-04 17:23:48');
INSERT INTO expense_records.c_code (id, category, code, `describe`, create_by, create_time, update_by, update_time) VALUES ('2', 'CATEGORY', 'ROLE', '角色分类', '1', '2025-09-27 23:44:59', '1', '2025-09-27 23:45:03');
