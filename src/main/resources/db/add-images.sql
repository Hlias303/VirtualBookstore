-- This script updates books.sql with image data from your local image files.
-- Instructions: From D:\VirtualBookstore folder, open PowerShell or a command-line tool that reads binary to hex/base64 (see below).
-- OR simply copy each UPDATE statement into pgAdmin and paste the corresponding base64 hex of your image file here after reading it.

-- UPDATE Atomic Habits with its cover image from folder D:\VirtualBookstore\IMAGES\tmp
UPDATE books 
SET imageData = decode(E'...base64-or-hex-encoding-of-your-file-Atomic_habits.jpg...', 'hex'),
    imageName = E'./tmp/Atomic_habits.jpg',  -- or keep original filename 'Atomic_habits.jpg' without directory part
    imageType = 'image/jpeg'
WHERE name = 'Atomic Habits';

-- UPDATE The Silent Patient 
UPDATE books SET imageData = decode(E'...', 'hex'), imageName = E'The_Silent_Patient.jpg' WHERE name = 'The Silent Patient'; 

-- UPDATE To Kill a Mockingbird  
UPDATE books SET imageData = decode(E'...', 'hex'), imageName = E'To_Kill_a_Mockingbird...(first_edition_cover).jpg' WHERE name = 'To Kill a Mockingbird';
