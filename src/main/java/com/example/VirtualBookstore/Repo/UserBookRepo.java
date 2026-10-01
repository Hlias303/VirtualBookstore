package com.example.VirtualBookstore.Repo;

import com.example.VirtualBookstore.Model.User;
import com.example.VirtualBookstore.Model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing user-book associations (readings/borrowed books).
 * Tracks which users have read which books and maintains the user-book relationship.
 * Extends Spring Data's JpaRepository for full CRUD operations on UserBook entities.
 */
public interface UserBookRepo extends JpaRepository<UserBook, Integer> {

    /**
     * Finds a UserBook entry associated with the given user.
     *
     * @param user the User entity to match against
     * @return the UserBook entry associated with the user
     */
    UserBook user(User user);

    /**
     * Retrieves the distinct set of all book IDs that have been associated with any user.
     * Useful for fetching all books that exist in the reading database.
     *
     * @return a list of unique book IDs from the UserBook table
     */
    @Query("SELECT DISTINCT ub.book.id FROM UserBook ub")
    List<Integer> findAllBookIds();

    /**
     * Finds all books that a specific user has borrowed or read.
     *
     * @param userId the unique identifier of the user
     * @return a list of UserBook entries where the user is the borrower
     */
    List<UserBook> findByUserId(Integer userId);

    /**
     * Finds an existing UserBook entry by both the user and book IDs.
     * Returns an Optional that may be empty if no association exists.
     *
     * @param userId   the unique identifier of the user
     * @param bookId   the unique identifier of the book
     * @return an Optional containing the UserBook entry if one exists, or empty otherwise
     */
    Optional<UserBook> findByUserIdAndBookId(int userId, int bookId);
}
