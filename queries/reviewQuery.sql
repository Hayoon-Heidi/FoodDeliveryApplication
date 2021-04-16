
-- get reviews by restaurant -> 메뉴별로 리뷰가 선택당해서 중복 발생 ㅠ
SELECT * FROM review INNER JOIN order_menu
ON review.orderId = order_menu.orderId
INNER JOIN restmenu 
ON order_menu.menuId = restmenu.menuId
WHERE restId = 1;


-- get reviews by restaurant, group by orderId with menus listed
SELECT * FROM review INNER JOIN order_menu
ON review.orderId = order_menu.orderId
INNER JOIN restmenu 
ON order_menu.menuId = restmenu.menuId
WHERE restId = 2
group by reviewId, order_menu.menuId;
-- 나머지는 컨트롤러랑 jsp에서 알아서..


--get total ratings by restaurant, by order
SELECT AVG(rate), restId, review.orderId, COUNT(restmenu.menuId) FROM review INNER JOIN order_menu
ON review.orderId = order_menu.orderId
INNER JOIN restmenu 
ON order_menu.menuId = restmenu.menuId
WHERE restId = 2
group by restId, review.orderId;

-- get sum of rate and count of ordermenus
SELECT SUM(rate), SUM(rate)/COUNT(restmenu.menuId), restId, COUNT(restmenu.menuId) FROM review INNER JOIN order_menu
ON review.orderId = order_menu.orderId
INNER JOIN restmenu 
ON order_menu.menuId = restmenu.menuId
WHERE restId = 2
group by restId;

--저거 오더메뉴 카운트 틀려서 다시함;
SELECT restId, SUM(rate) AS sum, COUNT(reviewId) AS count 
    FROM review INNER JOIN orders
    ON review.orderId = orders.orderId
    INNER JOIN order_menu
    ON orders.orderId = order_menu.orderId
    INNER JOIN restmenu 
    ON order_menu.menuId = restmenu.menuId
    WHERE restId = 1
    group by restId;
 

 SELECT restId, reviewId, SUM(rate) AS sum, COUNT(review.reviewId) AS count 
    FROM review INNER JOIN orders
    ON review.orderId = orders.orderId
    INNER JOIN order_menu
    ON orders.orderId = order_menu.orderId
    INNER JOIN restmenu 
    ON order_menu.menuId = restmenu.menuId
    group by restId, reviewId;
 

SELECT SUM(sum / count) AS sum, COUNT(sum / count) AS count FROM
(SELECT orders.orderId, restId, reviewId, SUM(rate) AS sum, COUNT(review.reviewId) AS count 
    FROM review INNER JOIN orders
    ON review.orderId = orders.orderId
    INNER JOIN order_menu
    ON orders.orderId = order_menu.orderId
    INNER JOIN restmenu 
    ON order_menu.menuId = restmenu.menuId
    group by restId, reviewId, orders.orderId)
WHERE restId = 1
GROUP BY restId;
 
 