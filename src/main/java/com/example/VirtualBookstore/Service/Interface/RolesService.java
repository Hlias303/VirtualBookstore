package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.User;

import java.util.List;

/**
 * Service interface for managing user accounts and registrations.
 * Handles saving new user accounts, deleting users, listing all users,
 * and creating admin accounts during application setup.
 */
public interface RolesService {

    /**
     * Saves a newly registered user to the database.
     * Includes user authentication, password encoding, and role assignment.
     *
     * @param user the User object with registration details to save
     * @throws Exception if there is an error during account creation
     */
    void SaveUser(User user) throws Exception;

    /**
     * Deletes a user account by their unique ID.
     *
     * @param id the unique ID of the user to delete
     */
    void DeleteUser(int id);

    /**
     * Retrieves a complete list of all registered users.
     *
     * @return a list of all User objects currently in the system
     */
    List<User> ShowUsers();

    /**
     * Creates an administrator account with the necessary privileges
     * during system initialization or for administrative setup.
     *
     * @param user the User object containing admin details to save
     */
    void SaveAdmin(User user);
}
