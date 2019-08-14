--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT * FROM products AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name LIKE '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM products AS p
WHERE date_part('year', p.expiration_date) = date_part('year', now() + INterval '1 mONth')
AND date_part('mONth', p.expiration_date) = date_part('mONth', now() + INterval '1 mONth');

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM products
WHERE price = (SELECT MAX(price) FROM products);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, COUNT(*) FROM products AS p
INNER JOIN type AS t ON p.type_id = t.id
GROUP BY t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM products AS p
INNER JOIN type AS t ON t.id = p.type_id
WHERE t.name IN ('СЫР', 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name FROM products AS p
INNER JOIN type AS t ON t.id = p.type_id
GROUP BY t.name HAVING COUNT(*) < 10;

--8. Вывести все продукты и их тип.*/
SELECT p.*, t.name FROM products AS p
INNER JOIN type AS t ON t.id = p.type_id