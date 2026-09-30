package com.example.VirtualBookstore.Repo;

import com.example.VirtualBookstore.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Repository interface for managing roles associated with users.
 * Extends Spring Data's JpaRepository for standard CRUD operations.
 * Provides a method to retrieve all roles by name for filtering purposes.
 */
public interface RolesRepo extends JpaRepository<Roles, Integer> {

    /**
     * Finds all roles that contain the given substring in their role name.
     *
     * @param name the partial role name to search for
     * @return a set of Roles whose name contains the specified substring (case-insensitive)
     */
    Set<Roles> findByName(String name);
}
