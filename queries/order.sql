-- DROP TABLE IF EXISTS order_menu;
-- DELETE FROM orders WHERE orderId = 1;
-- DROP TABLE IF EXISTS orders;

DROP TABLE IF EXISTS order_menu;
DROP TABLE IF EXISTS orders;

CREATE TABLE orders(
    orderId INT AUTO_INCREMENT,
    customerId INT NOT NULL,
    driverId INT,
    orderedTime DATETIME NOT NULL,
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



INSERT INTO orders(customerId, driverId, orderedTime)
VALUES (1, null, '2020-11-01 12:34:00'),
    (1, null, '2020-11-02 15:30:04');

INSERT INTO order_menu(menuId, orderId, quantity)
VALUES (2, 1, 1),
(5, 1, 2),
(6, 3, 1),
(7, 3, 1),
(9, 3, 3);

-- get subtotal of each menu
SELECT orderId, restMenu.menuName AS name, 
order_menu.quantity AS quantity,
restMenu.menuPrice * order_menu.quantity AS subtotal 
FROM order_menu
INNER JOIN restMenu ON order_menu.menuId = restMenu.menuId;

-- get subtotal with more details
SELECT orderId, restMenu.menuId, quantity, menuName, menuPrice, 
restMenu.menuPrice * order_menu.quantity AS subtotal 
FROM order_menu
INNER JOIN restMenu ON order_menu.menuId = restMenu.menuId;

-- get subtotal with more details by customer
SELECT orders.orderId AS orderId, customerId, restMenu.menuId AS menuId, 
quantity, menuName, menuPrice, 
restMenu.menuPrice * order_menu.quantity AS subtotal 
FROM order_menu
INNER JOIN restMenu ON order_menu.menuId = restMenu.menuId
INNER JOIN orders ON order_menu.orderId = orders.orderId
WHERE customerId = 1 AND orders.orderId = 1;
-- ORDER BY customerId, orderId;

-- orderId에서 for문 돌려서..

-- get all orderId that a customer ordered
SELECT orderId FROM orders WHERE customerId = 1;

-- get total of an order
SELECT orderId, SUM(restMenu.menuPrice * order_menu.quantity) AS total 
FROM order_menu
INNER JOIN restMenu ON order_menu.menuId = restMenu.menuId
GROUP BY orderId;

-- get currently made order id to insert orderMenu
SELECT orderId FROM orders
WHERE customerId=6 AND driverId IS NULL AND orderedTime='2020-11-07 08:26:00.292';