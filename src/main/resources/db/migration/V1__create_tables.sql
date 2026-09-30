-- Flyway Migration: V1 - Create All Tables for VirtualBookstore Database
-- ============================================================================

-- ========== BOOKS TABLE ==========
CREATE TABLE IF NOT EXISTS books (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE NOT NULL,
    price INT NOT NULL,
    image_name VARCHAR(255),
    image_type VARCHAR(100),
    image_data BYTEA
);

-- ========== USERS TABLE ==========
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- ========== ROLES TABLE ==========
CREATE TABLE IF NOT EXISTS roles (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- ========== USER_ROLE RELATIONSHIP TABLE (Many-to-Many Join Table) ==========
CREATE TABLE IF NOT EXISTS user_roles (
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id INT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- ========== USER_BOOKS TABLE (User's Reading/Rating History) ==========
CREATE TABLE IF NOT EXISTS user_books (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    book_id INT NOT NULL REFERENCES books(id) ON DELETE CASCADE,
    sentiment DOUBLE PRECISION,
    UNIQUE (user_id, book_id)  -- A user can only rate one time per book
);

-- CREATE INDEXES FOR BETTER PERFORMANCE
CREATE INDEX idx_user_books_user_id ON user_books(user_id);
CREATE INDEX idx_user_books_book_id ON user_books(book_id);

-- ========== COMMENTS / INFO (These are stored in PG catalog, not actual tables) ==========
COMMENT ON TABLE users IS 'Stores registered account information including usernames and hashes';
COMMENT ON TABLE roles IS 'Role definitions for system-level access control (ADMIN/USER roles)';
COMMENT ON TABLE user_roles IS 'Junction table linking users to specific roles via foreign key relationships';
COMMENT ON TABLE books IS 'Covers book metadata: name, description, release date, price, images';
COMMENT ON TABLE user_books IS 'Represents which books have been read/rated by each user (ratings and sentiment)';
