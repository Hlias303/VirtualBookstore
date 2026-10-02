-- Simple INSERT: sample ratings, same style as books.sql
-- Run AFTER users.sql and books.sql

INSERT INTO user_book (user_id, book_id, sentiment)
SELECT
    (SELECT id FROM users WHERE username = v.username),
    (SELECT id FROM books WHERE name = v.book_name),
    v.sentiment
FROM (VALUES
    ('Hlias', 'The Silent Patient', 5.0),
    ('Hlias', 'Atomic Habits', 4.5),
    ('Nikos', 'The Silent Patient', 4.0),
    ('Nikos', 'To Kill a Mockingbird', 5.0),
    ('Nikos', '1984', 3.5),
    ('Kostas', 'Atomic Habits', 5.0),
    ('Kostas', 'To Kill a Mockingbird', 4.8),
    ('Kostas', '1984', 3.9),
    ('Marios', 'To Kill a Mockingbird', 4.7),
    ('Marios', 'Atomic Habits', 5.0),
    ('Marios', 'The Alchemist', 4.6),
    ('Petros', '1984', 5.0),
    ('Petros', 'To Kill a Mockingbird', 4.9),
    ('Petros', 'The Silent Patient', 4.2)
) AS v(username, book_name, sentiment)
WHERE NOT EXISTS (
    SELECT 1 FROM user_book ub
    WHERE ub.user_id = (SELECT id FROM users WHERE username = v.username)
      AND ub.book_id = (SELECT id FROM books WHERE name = v.book_name)
);
