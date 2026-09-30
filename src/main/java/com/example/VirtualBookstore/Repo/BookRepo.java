package com.example.VirtualBookstore.Repo;

import com.example.VirtualBookstore.Model.Books;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing book entities.
 * Provides methods to query books by collection IDs and search books by keyword.
 * Extends Spring Data's JpaRepository for full CRUD operations.
 */
@Repository
public interface BookRepo extends JpaRepository<Books, Integer> {

    /**
     * Finds all books that belong to any of the given collection IDs.
     *
     * @param ids list of collection IDs to filter books by
     * @return a list of Books matching any of the given collection IDs
     */
    List<Books> findByIdIn(List<Integer> ids);

    /**
     * Searches for books by a keyword.
     * Performs a case-insensitive partial match on the book name.
     *
     * @param keyword the search keyword (at least 1 character)
     * @return a list of Books whose name contains the keyword (case-insensitive)
     */
    @Query("SELECT b from Books b WHERE " +
            "LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Books> SearchBook(String keyword);
}
