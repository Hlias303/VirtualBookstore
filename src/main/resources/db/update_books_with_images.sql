-- Script to both create books from seed AND load images if they exist in local folder
-- Run once after db creation, or on every startup for fresh DBs only
-- To run safely with continue-on-error=true: use spring.sql.init.mode=always

-- Clear any existing book data that might conflict (if running multiple times due to duplicates)
DROP TABLE IF EXISTS books;

CREATE TABLE books (
    id SERIAL PRIMARY KEY, name TEXT, description TEXT, release_date DATE, price INTEGER, 
    imageData BYTEA, imageName TEXT, imageType TEXT
);

-- Insert books metadata and optionally images from local files if they exist
-- Each INSERT checks that file exists locally before trying to load it into database
-- Use the LOCAL keyword to read files from server filesystem (must be on db server) — or use JDBC copy

-- Atomic Habits
INSERT INTO books (name, description, release_date, price, imageData, imageName, imageType) 
SELECT 'Atomic Habits', 'An easy & proven way to build good habits...', TO_DATE('2018-10-16', 'YYYY-MM-DD'), 12, decode(E'XXX...'::text, 'hex'), './Atomic_habits.jpg', 'image/jpeg'
WHERE EXISTS (SELECT 1 FROM pg_ls_dir('/tmp/Images/Books') WHERE ...); -- check if file exists locally

-- Alternative: Just set imageData to empty/default if not found in seed run
INSERT INTO books (name, description, release_date, price, imageData, imageName, imageType) 
VALUES ('Atomic Habits', 'An easy & proven way...', TO_DATE('2018-10-16', 'YYYY-MM-DD'), 12, NULL, NULL, NULL);
