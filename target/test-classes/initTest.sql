DROP TABLE IF EXISTS mobile_phone_to_store;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS phone_user;
DROP TABLE IF EXISTS mobile_phone;
CREATE TABLE IF NOT EXISTS mobile_phone
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    brand       VARCHAR(64),
    model       VARCHAR(64),
    performance INT,
    price       INT
);
CREATE TABLE IF NOT EXISTS phone_user
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(30),
    mobile_phone_id INT,
    FOREIGN KEY (mobile_phone_id) REFERENCES mobile_phone (id)
);
CREATE TABLE store
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
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
       ('SAMSUNG', 'Galaxy Note21', 11, 1500),
       ('Nokia', '5310DS', 2, 150);
INSERT INTO phone_user (name, mobile_phone_id)
VALUES ('Рюрик В.Д.', 2),
       ('Вовин И.Ж.', NULL),
       ('Жириновская Р.Л.', 1),
       ('Гоголь Ю.А.', 3),
       ('Цветкова Д.Д.', 2);
INSERT INTO store (name)
VALUES ('APPLE_Store'),
       ('SAMSUNG'),
       ('MVideo');
INSERT INTO mobile_phone_to_store(store_id, mobile_phone_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);