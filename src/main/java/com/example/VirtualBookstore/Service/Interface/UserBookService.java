package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.UserBook;

import java.util.List;

/**
 * Service interface for managing user-book associations (readings and borrowing history).
 * Tracks which users have read or are borrowing books for recommendation purposes.
 */
public interface UserBookService {

    /**
     * Retrieves all user-book associations from the database.
     * Returns the complete mapping of users to the books they have read or borrowed.
     *
     * @return a list of all UserBook entries (user-book pairs)
     */
    List<UserBook> ShowUserBooks();

    /**
     * Adds a reading entry to track a user's interaction with a book.
     * Records that a user has read a specific book for later recommendation analysis.
     *
     * @param userbook the UserBook object representing the user and book to record
     * @param bookId   the ID of the book the user is marking as read
     */
    void AddSentiment(UserBook userbook, int bookId);

    /**
     * Retrieves a specific user-book entry by the user's associated book ID.
     * Used to look up reading status or borrowing history for individual books.
     *
     * @param bookId the book ID whose user-book association to look up
     * @return the UserBook entry associated with the given book ID
     */
    UserBook ShowUserBook(int bookId);
}
