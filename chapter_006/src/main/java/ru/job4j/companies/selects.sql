--1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
SELECT p.name, c.name FROM persons AS p
INNER JOIN companies AS c ON p.company_id = c.id
WHERE p.company_id != 5;

-- 2) Select the name of the company with the maximum number of persons + number of persons in this company
SELECT c.name, m.count FROM companies AS c
INNER JOIN (SELECT company_id, count(company_id) FROM persons
GROUP BY company_id
ORDER BY count DESC LIMIT 1) AS m
ON m.company_id = c.id;

