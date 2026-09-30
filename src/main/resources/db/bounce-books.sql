-- Run this only if you've manually loaded images OR want fresh DB without image duplication  
SELECT 'Books have NULL imageData until you run certutil and paste UPDATE commands' AS note;

-- Books seed data with empty imageData fields for now
INSERT INTO books (name, description, release_date, price, imageData, imageName, imageType) 
VALUES ('Atomic Habits', 'An easy & proven way to build good habits & break bad ones.', TO_DATE('2018-10-16', 'YYYY-MM-DD'), 12, NULL, NULL, NULL);

-- etc for each book...