-- Flyway Migration: V2 - Insert Sample Data
-- ============================================================================
-- Run after V1__create_tables.sql has created all tables
-- ============================================================================

-- ========== INSERT SAMPLE ROLES ==========
INSERT INTO roles (name) VALUES 
    ('USER'),
    ('ADMIN');

-- ========== INSERT SAMPLE USERS ==========
-- Password: 1111 (stored as plain text - encrypt in production!)
INSERT INTO users (username, password) VALUES 
    ('Hlias', '1111'),
    ('Nikos', '1111'),
    ('Kostas', '1111'),
    ('Marios', '1111'),
    ('Petros', '1111');

-- ========== INSERT USER-ROLE RELATIONSHIPS ==========
-- User ID 1 (Hlias) = ADMIN, User IDs 2-5 = USER
INSERT INTO user_roles (user_id, role_id) VALUES 
    (1, 2),  -- Hlias = ADMIN
    (2, 1),  -- Nikos = USER
    (3, 1),  -- Kostas = USER
    (4, 1),  -- Marios = USER
    (5, 1);  -- Petros = USER

-- ========== INSERT SAMPLE BOOKS ==========
INSERT INTO books (name, description, release_date, price) VALUES 
    ('The Silent Patient', 'A psychological thriller about a woman''s act of violence against her husband - and of the therapist obsessed with uncovering her motive.', '2019-02-05', 15),
    ('Atomic Habits', 'An easy & proven way to build good habits & break bad ones.', '2018-10-16', 12),
    ('To Kill a Mockingbird', 'A novel of warmth and humor despite dealing with serious issues of rape and racial inequality.', '1960-07-11', 10),
    ('1984', 'A dystopian social science fiction novel and cautionary tale about the dangers of totalitarianism.', '1949-06-08', 9),
    ('The Alchemist', 'A philosophical book about a shepherd''s journey to fulfill his personal legend.', '1988-04-15', 11);

-- ========== INSERT SAMPLE USER BOOK RATINGS ==========
INSERT INTO user_books (user_id, book_id, sentiment) VALUES 
    (1, 1, 4.5),
    (1, 2, 5.0),
    (2, 1, 4.0),
    (2, 3, 3.5),
    (3, 2, 4.5),
    (3, 4, 5.0),
    (4, 3, 4.0),
    (4, 5, 3.0),
    (5, 4, 4.5),
    (5, 5, 5.0);
