SELECT c.*, t.name, e.name, b.name FROM cars AS c
INNER JOIN transmissions AS t ON t.id = c.transmission
INNER JOIN engines AS e ON c.engine = e.id
INNER JOIN bodies AS b ON c.body = b.id;

SELECT name FROM
	(SELECT t.name FROM transmissions AS t
	LEFT OUTER JOIN cars AS c ON c.transmission = t.id
	WHERE c.id is null) AS a
	UNION
	(SELECT e.name FROM engines AS e
	LEFT OUTER JOIN cars AS c ON c.engine = e.id
	WHERE c.id is null)
	UNION
	(SELECT b.name FROM bodies AS b
	LEFT OUTER JOIN cars AS c ON c.body = b.id
	WHERE c.id is null);