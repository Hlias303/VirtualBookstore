package com.example.VirtualBookstore.Repo;

import com.example.VirtualBookstore.Model.User;
import com.example.VirtualBookstore.Model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing user accounts in the application.
 * Handles user registration, authentication, and profile lookup operations.
 * Provides Spring Data defaults for full CRUD plus custom user-specific queries.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    /**
     * Finds a user by their username for login/authentication purposes.
     *
     * @param username the login username to search for
     * @return the User object if found, or null if no user with that username exists
     */
    User findByUsername(String username);

    /**
     * Checks whether a user with the given username already exists in the database.
     * Used during registration to prevent duplicate accounts.
     *
     * @param username the username to check for existence
     * @return true if a user with the given username exists, false otherwise
     */
    @Query("select u from User u where name = ?1")
    boolean existsByusername(String username);
}
