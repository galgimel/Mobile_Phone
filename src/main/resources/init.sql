DROP TABLE IF EXISTS mobile_phone_to_store;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS phone_user;
DROP TABLE IF EXISTS mobile_phone;
CREATE TABLE IF NOT EXISTS mobile_phone
(
    id          SERIAL PRIMARY KEY,
    brand       VARCHAR(64),
    model       VARCHAR(64),
    performance INT,
    price       INT
);
CREATE TABLE IF NOT EXISTS phone_user
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(30),
    mobile_phone_id INT,
    FOREIGN KEY (mobile_phone_id) REFERENCES mobile_phone (id)
);
CREATE TABLE store
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30)
);
CREATE TABLE mobile_phone_to_store
(
    store_id        int,
    mobile_phone_id INT,
    FOREIGN KEY (store_id) REFERENCES store (id),
    FOREIGN KEY (mobile_phone_id) REFERENCES mobile_phone (id)
);
INSERT INTO mobile_phone (brand, model, performance, price)
VALUES ('iPhone', '14PRO', 11, 1500),
       ('iPhone', '14', 10, 1400),
       ('iPhone', '12PRO', 10, 1300),
       ('iPhone', '12', 9, 1200),
       ('iPhone', 'R', 8, 1000),
       ('SAMSUNG', 'Galaxy Note21', 11, 1500),
       ('SAMSUNG', 'Galaxy S21+', 10, 1350),
       ('SAMSUNG', 'Galaxy S21', 10, 1200),
       ('SAMSUNG', 'Galaxy S20+', 9, 1100),
       ('SAMSUNG', 'Galaxy S20', 9, 1000),
       ('Xiaomi', '12PRO', 10, 1300),
       ('Huawei', 'Xs2', 11, 1700),
       ('Huawei', 'P50 Pocket', 11, 1600),
       ('Realme', 'GT2 PRO12', 9, 1200),
       ('Nokia', '5310DS', 2, 150);
INSERT INTO phone_user (name, mobile_phone_id)
VALUES ('Рюрик В.Д.', 6),
       ('Карп Е.Г.', 2),
       ('Медоедов К.К.', 4),
       ('Абрамова Н.З.', 4),
       ('Зобин Ш.В.', NULL),
       ('Вовин И.Ж.', NULL),
       ('Жириновская Р.Л.', 1),
       ('Каменьщик Р.Т.', 1),
       ('Бобик И.Н.', 3),
       ('Вайб М.П.', 3),
       ('Хохоль Р.Ж.', 3),
       ('Гоголь Ю.А.', 15),
       ('Цветкова Д.Д.', 6);
INSERT INTO store (name)
VALUES ('APPLE_Store'),
       ('SAMSUNG'),
       ('MVideo');
INSERT INTO mobile_phone_to_store(store_id, mobile_phone_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (2, 6),
       (2, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (3, 1),
       (3, 2),
       (3, 6),
       (3, 7),
       (3, 8);