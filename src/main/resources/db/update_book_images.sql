-- Update books with images stored as bytea literals from local files
\c 'host=localhost dbname=BookDB user=postgres password=1111'

-- Note: These commands load images into the images/bookstore directory. 
-- Run this after ensuring those image paths exist, then execute manually or copy each command below to psql

\noecho Updating books from local image files...
-- Atomic Habits
UPDATE books SET imageData = decode('placeholder_for_actual_bytes','hex'), imageName = 'Atomic_habits.jpg', imageType = 'image/jpeg' WHERE name = 'Atomic Habits';
-- Silent Patient  
UPDATE books SET imageData = decode('placeholder_for_actual_bytes','hex'), imageName = '913A1+rl-gL._UF1000,1000_QL80_.jpg', imageType = 'image/jpeg' WHERE name = 'The Silent Patient';
-- Mockingbird
UPDATE books SET imageData = decode('placeholder_for_actual_bytes','hex'), imageName = 'To_Kill_a_Mockingbird_(first_edition_cover).jpg', imageType = 'image/jpeg' WHERE name = 'To Kill a Mockingbird';
