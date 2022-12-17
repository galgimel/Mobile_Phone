DROP TABLE IF EXISTS mobile_phones CASCADE;
CREATE TABLE IF NOT EXISTS mobile_phones (id SERIAL PRIMARY KEY, brand VARCHAR(64), model  VARCHAR(64), performance INT, price INT);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '14PRO', 11, 1500);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '14', 10, 1400);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '12PRO', 10, 1300);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '12', 9, 1200);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', 'R', 8, 1000);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy Note21', 11, 1500);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S21+', 10, 1350);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S21', 10, 1200);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S20+', 9, 1100);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S20', 9, 1000);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Xiaomi', '12PRO', 10, 1300);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Huawei', 'Xs2', 11, 1700);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Huawei', 'P50 Pocket', 11, 1600);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Realme', 'GT2 PRO12', 9, 1200);
INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Nokia', '5310DS', 2, 150);