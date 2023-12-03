-- Создание таблицы class_of_room
CREATE TABLE class_of_room (
                               id_class_of_room SERIAL PRIMARY KEY,
                               class_room_en VARCHAR(20) NOT NULL,
                               class_room_uk VARCHAR(45) NOT NULL,
                               amount INT CHECK (amount >= 0),
                               guests INT CHECK (guests >= 0),
                               price INT CHECK (price >= 0)
);

-- Заполнение данных в таблице class_of_room
INSERT INTO class_of_room (class_room_en, class_room_uk, amount, guests, price)
VALUES
    ('unknown', 'невідомо', 0, 0, 0),
    ('luxe', 'люкс', 2, 2, 5500),
    ('comfort', 'комфорт', 3, 3, 2000),
    ('basic', 'звичайний', 5, 2, 1000),
    ('econom', 'економ', 5, 2, 750);

-- Создание таблицы rooms
CREATE TABLE rooms (
                       id_room SERIAL PRIMARY KEY,
                       id_class_of_room INT UNIQUE NOT NULL,
                       image VARCHAR(255) NOT NULL,
                       status_en VARCHAR(255),
                       status_uk VARCHAR(255),
                       FOREIGN KEY (id_class_of_room) REFERENCES class_of_room (id_class_of_room)
);

-- Заполнение данных в таблице rooms
INSERT INTO rooms (id_class_of_room, image, status_en, status_uk)
VALUES
    (1, '/images/luxe_room.png', 'booked', 'заброньований'),
    (2, '/images/comfort_room.png', 'booked', 'заброньований');

CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        fname VARCHAR(45),
                        lname VARCHAR(45),
                        login VARCHAR(45),
                        password VARCHAR(45),
                        dob DATE,
                        mobile VARCHAR(45),
                        role VARCHAR(10)
);

-- Создание таблицы orders
CREATE TABLE orders (
                        id_order SERIAL PRIMARY KEY,
                        id_user INT NOT NULL,
                        date_of_settlement DATE NOT NULL,
                        date_of_departure DATE NOT NULL,
                        id_room INT NOT NULL,
                        payment_status_en VARCHAR(45) NOT NULL,
                        payment_status_uk VARCHAR(45) NOT NULL,
                        date_of_create_order DATE NOT NULL,
                        amount_of_guests INT NOT NULL,
                        room_type VARCHAR(45) NOT NULL,
                        FOREIGN KEY (id_room) REFERENCES rooms (id_room),
                        FOREIGN KEY (id_user) REFERENCES "user" (id)
);

-- Заполнение данных в таблице orders
INSERT INTO orders (id_user, date_of_settlement, date_of_departure, id_room, payment_status_en, payment_status_uk, date_of_create_order, amount_of_guests, room_type)
VALUES
    (6, '2022-05-21', '2022-05-28', 1, 'expired', 'протермінований', '2022-05-10', 0, 'null');

-- Заполнение данных в таблице rooms
INSERT INTO rooms (id_class_of_room, image, status_en, status_uk)
VALUES
    (0, 'EMPTY_ROOM', 'available', 'доступний');


-- Заполнение данных в таблице "user"
INSERT INTO "user" (fname, lname, login, password, dob, mobile, role)
VALUES
    ('Ivanko', 'Babuk', 'm@ga.as', 'DAsad2ad', '2002-08-15', '0986087609', 'user');

INSERT INTO orders (id_user, date_of_settlement, date_of_departure, id_room, payment_status_en, payment_status_uk, date_of_create_order, amount_of_guests, room_type)
VALUES
    (1, '2022-05-21', '2022-05-28', 1, 'expired', 'протермінований', '2022-05-10', 0, 'null'); -- Replace '1' with an existing id from the "user" table

-- Заполнение данных в таблице orders (продолжение)
INSERT INTO orders (id_user, date_of_settlement, date_of_departure, id_room, payment_status_en, payment_status_uk, date_of_create_order, amount_of_guests, room_type)
VALUES
    (1, '2022-05-13', '2022-05-21', 1, 'expired', 'протермінований', '2022-05-10', 0, 'null'),
    (1, '2022-05-14', '2022-05-21', 2, 'expired', 'протермінований', '2022-05-05', 0, 'null');

-- Заполнение данных в таблице rooms (продолжение)
INSERT INTO rooms (id_class_of_room, image, status_en, status_uk)
VALUES
    (1, '/images/luxe_room.png', 'booked', 'заброньований'),
    (2, '/images/comfort_room.png', 'booked', 'заброньований');

-- Заполнение данных в таблице "user" (продолжение)
INSERT INTO "user" (fname, lname, login, password, dob, mobile, role)
VALUES
    ('Ihor', 'Bober', 'bober@gmail.com', 'fasvaNMW21', '2002-08-15', '0986087609', 'user'),
    ('Sergiy', 'Olejuk', 'user1@gmail.com', 'nasaMAasd12', '2002-08-15', '0986087609', 'user');

