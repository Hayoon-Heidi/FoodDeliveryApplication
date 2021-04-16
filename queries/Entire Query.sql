DROP TABLE IF EXISTS order_menu;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS restmenu;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS category;

CREATE TABLE customers(
 id INTEGER PRIMARY KEY AUTO_INCREMENT,
 email VARCHAR(50),
 password VARCHAR(16),
 firstName VARCHAR(30),
 lastName VARCHAR(30),
 address VARCHAR(60),
 phoneNumber VARCHAR(13)
);

CREATE TABLE category(
 catId INTEGER PRIMARY KEY AUTO_INCREMENT,
 catName VARCHAR(30)
);

CREATE TABLE restaurants(
 id INTEGER PRIMARY KEY AUTO_INCREMENT,
 email VARCHAR(50),
 password VARCHAR(16),
 restName VARCHAR(30),
 open VARCHAR(16),
 close VARCHAR(16),
 description VARCHAR(255),
 address VARCHAR(60),
 phoneNumber VARCHAR(11),
 category INTEGER,
 restImg VARCHAR(255),
 FOREIGN KEY (category) REFERENCES category (catId)
);

CREATE TABLE restMenu (
    menuId INT AUTO_INCREMENT,
    restId INT NOT NULL,
    menuName VARCHAR(50),
    menuDesc VARCHAR(50),
    menuPrice DOUBLE(5),
    menuImg VARCHAR(255),
    PRIMARY KEY (menuId),
    FOREIGN KEY (restId) REFERENCES restaurants(id)
);

CREATE TABLE orders(
    orderId INT AUTO_INCREMENT,
    customerId INT NOT NULL,
    driverId INT,
    orderedTime DATETIME NOT NULL,
    orderStatus VARCHAR(30) NOT NULL,
    orderStatusId INT NOT NULL,
    PRIMARY KEY (orderId),
    FOREIGN KEY (customerId) REFERENCES customers (id)
);

CREATE TABLE order_menu(
    menuId INT NOT NULL,
    orderId INT NOT NULL,
    quantity INT,
    PRIMARY KEY (menuId, orderId),
    FOREIGN KEY (menuId) REFERENCES restMenu (menuId),
    FOREIGN KEY (orderId) REFERENCES orders (orderId)
);

CREATE TABLE review(
 reviewId INT AUTO_INCREMENT,
 orderId INT NOT NULL,
 reviewContent VARCHAR(50),
 rate INT,
 PRIMARY KEY (reviewId),
 FOREIGN KEY (orderId) REFERENCES orders(orderid)
);

INSERT INTO customers(email, password, firstName, lastName, address, phoneNumber) 
VALUES ('shiny7@student.douglascollege.ca', 'qwerty1234', 'Yerin', 'Shin', '700 Royal Ave, New Westminster, BC', '604-527-5400'),
('new@email.com', 'new', 'New', 'Customer', '700 Royal Ave, New Westminster, BC', '778-123-4567');

insert into category (catId, catName) values (1, 'Chicken');
insert into category (catId, catName) values (2, 'Pizza');
insert into category (catId, catName) values (3, 'Sub / Salad');
insert into category (catId, catName) values (4, 'Fast Food');
insert into category (catId, catName) values (5, 'Cafe / Bakery');
insert into category (catId, catName) values (6, 'Korean');
insert into category (catId, catName) values (7, 'Asian');
insert into category (catId, catName) values (8, 'Vegetarian'); 

INSERT INTO restaurants(email, password, restName, open, close, description, address, phoneNumber, category, restImg) 
VALUES ('fast@food.com', 'fastfood', 'Good Fast Food', '11:00:00', '23:00:00', 'FastFood Restaurant', '700 Royal Ave', '1234567890', '4', '/static/img/restaurant/fast_food_logo.png'), 
('example@example.com', 'example123', '4:30AM Cafe', '8:00', '22:00', 'This is an example', '123 example St., EX', '0000000000', '5', '/static/img/svg_icon.png'),
('p@email.com', 'pig', 'Pigrates', '18:00', '02:00', 'Kool restaurant', '212 Gyeyangsan-ro, Gyeyang-gu, Incheon, Republic of Korea', '0322215858', '6', '/static/img/restaurant/pigrates.jpg'),
('bubble@email.com', 'bubble', 'Bubble World', '12:00', '21:30', 'I love hotpot and bubble tea', '601 Agnes St, New Westminster, BC', '7783977800', '7', '/static/img/restaurant/bubbleworld_logo_hand_draw_colour.png'),
('chicken@email.com', 'chicken', 'Hi Five Chicken', '00:00', '23:59', 'Chicken!', '8669 10th Ave, Burnaby, BC', '6045409255', '1', '/static/img/restaurant/hi_five_chicken.png'),
('pizza@email.com', 'pizza', 'Pizza Garden', '11:00', '23:30', 'Pizza!', '201-800 Carnarvon St, New Westminster, BC', '6045203050', '2', '/static/img/restaurant/pizza_garden.png'),
('sub@email.com', 'sub', 'Subway', '7:00', '23:00', 'Subway', '800 Carnarvon St, New Westminster, BC', '6045494473', '3', '/static/img/restaurant/subway.png'),
('vegetarian@email.com', 'vegetarian', 'V-Cafe', '10:30', '19:00', 'Snug Vietnamese cafe', '789 Carnarvon St, New Westminster, BC', '6045538739', '8', '/static/img/restaurant/vcafe.png');





INSERT INTO restMenu(restId, menuName, menuDesc, menuPrice, menuImg) VALUES 
(2, 'example', 'This is an example', '7.25', ''),
(2, 'Americano', 'Classic coffee', 2.99, ''),
(2, 'Taro Milk Tea', 'Taro flavored milk tea', 4.0, ''),
(2, 'Orange Juice', 'Freshly squeezed orange', 3.8, ''),
(2, 'Chocolate Milk Tea	', 'Deep chocolate goood', 4.0, ''),
(1, 'Fried Chicken', 'Yummy Fried Chicken', 25.5, '/static/img/menu/fried_chicken.jpg'),
(1, 'Hawaian Pizza', 'Why put pineapples on pizza?!', 28.98, '/static/img/menu/hawaiian.jpg'),
(1, 'Pepperoni Pizza', 'Classic Pepperoni', 27.55, '/static/img/menu/pepperoni.jpg'),
(1, 'Coca Cola', 'Classic Coke', 2.99, '/static/img/menu/coke.jpg'),
(3, 'Schweinshaxe', 'Crispy Schweinshaxe with bean sprouts', 32.25, '/static/img/menu/schweinshaxe.jpg'),
(3, 'Lamb Chop Steak', 'Baaa', 32.25, '/static/img/menu/lambsteak.jpg'),
(3, 'Mussel Tomato Stew', 'Spicy tomato & Yeosu Bambada mussel stew', 19.0, '/static/img/menu/musselstew.jpg'),
(3, 'Italy Seafood Pasta', 'good', 24.0, '/static/img/menu/seafoodpasta.jpg'),
(3, 'Salted Pollack Roe Cream Pasta', 'Cream Pasta', 20.40, '/static/img/menu/creampasta.jpg'),
(3, 'Canada Dry', 'Everyone knows it', 2.99, '/static/img/menu/canadadry.jpg'),
(4, 'Spicy Fish Hotpot', 'Please deliver this to Korea plz', 24.5, ''),
(5, 'Fried Chicken', 'Just filling empty space', 26.99, ''),
(6, 'Meat Pizza', 'For meat lovers', 28.5, ''),
(7, 'Chicken Teriyaki Sub', 'Yummy', 11.68, '');


INSERT INTO orders(customerId, driverId, orderedTime, orderStatus, orderStatusId)
VALUES 
(1, null, '2019-07-13 18:34:02', 'Confirm the New order', 1),
(1, null, '2020-10-15 18:34:02', 'Confirm the New order', 1),
(1, null, '2020-11-01 12:34:00', 'Confirm the New order', 1),
(1, null, '2020-11-02 15:30:04', 'Confirm the New order', 1),
(2, null, '2020-11-02 17:30:04', 'Confirm the New order', 1),
(2, null, '2020-11-02 20:30:04', 'Start Delivery', 2),
(1, null, '2020-12-03 14:12:34', 'Confirm the New order', 1),
(1, null, '2020-11-30 12:34:56', 'Confirm the New order', 1),
(2, null, '2020-12-01 12:34:56', 'Confirm the New order', 1),
(2, null, '2020-12-03 12:40:40', 'Confirm the New order', 1),
(1, null, '2020-12-04 15:40:20', 'Confirm the New order', 1);

INSERT INTO order_menu(menuId, orderId, quantity)
VALUES 
(1, 1, 1),
(3, 1, 3),
(8, 2, 2),
(2, 3, 1),
(5, 4, 2),
(6, 5, 1),
(7, 5, 1),
(9, 5, 3),
(9, 6, 3),
(10, 7, 1),
(13, 7, 1),
(14, 7, 1),
(15, 7, 3),
(16, 8, 2),
(17, 9, 1),
(18, 10, 1),
(19, 11, 1);

INSERT INTO review(orderId, reviewContent, rate)
VALUES (1, 'The drinks were not warm enough', 3);

INSERT INTO review(orderId, reviewContent, rate)
VALUES (2, 'This is my favorite!', 4);

INSERT INTO review(orderId, reviewContent, rate)
VALUES (3, 'This americano is not that strong', 2);

INSERT INTO review(orderId, reviewContent, rate)
VALUES (4, 'Chololate is always right', 5),
(5, 'fried chicken was sooo good', 5);

INSERT INTO review(orderId, reviewContent, rate)
VALUES (7, 'So tasty', 5),
(8, 'Please sell this in Korea', 5),
(9, 'Chicken was a bit soggy', 3),
(10, '', 2),
(11, 'Good as always', 4);
