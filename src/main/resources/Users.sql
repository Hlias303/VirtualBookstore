-- Idempotent: only inserts users that do not exist yet (safe to run on every startup)
INSERT INTO users (password, username)
SELECT * FROM (VALUES
    ('1111', 'Hlias'),
    ('1111', 'Nikos'),
    ('1111', 'Kostas'),
    ('1111', 'Marios'),
    ('1111', 'Petros')
) AS v(password, username)
WHERE NOT EXISTS (SELECT 1 FROM users WHERE users.username = v.username);
