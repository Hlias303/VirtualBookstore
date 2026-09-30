-- Idempotent: only inserts roles that do not exist yet (safe to run on every startup)
INSERT INTO roles (name)
SELECT * FROM (VALUES ('USER'), ('ADMIN')) AS v(name)
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE roles.name = v.name);
