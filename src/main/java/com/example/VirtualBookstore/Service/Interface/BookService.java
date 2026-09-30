package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.Books;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for managing books in the application.
 * Handles book display, retrieval, creation, deletion,
 * upload, and search operations.
 */
public interface BookService {

    /**
     * Retrieves all books from the book catalog.
     *
     * @return a list of all available Books
     */
    List<Books> ShowAllBooks();

    /**
     * Retrieves a single book by its unique identifier.
     *
     * @param id the book's unique ID
     * @return the Book with the given ID, or throws an exception if not found
     */
    Books ShowBookById(int id);

    /**
     * Adds a new book to the catalog.
     *
     * @param book the Books object containing book details to add
     */
    void AddBook(Books book);

    /**
     * Deletes a book by its ID and removes associated file/image data.
     *
     * @param id the book's unique ID
     * @param book the Books object to delete (including image metadata)
     */
    void DeleteBook(int id, Books book);

    /**
     * Handles adding a new book or updating an existing book with a file upload.
     *
     * @param book the Books object containing book details and image
     * @param image the image file to upload (can be null for updates)
     * @throws IOException when there is an issue processing the image upload
     */
    void BookAddorUpdate(Books book, MultipartFile image) throws IOException;

    /**
     * Retrieves a specific book entry by its book ID.
     *
     * @param bookID the unique book ID
     * @return the Books object matching the given book ID
     */
    Books GetBookByID(int bookID);

    /**
     * Searches for books by keyword (partial match on book name/title).
     *
     * @param keyword the search keyword to filter books by name
     * @return a list of Books whose name contains the search keyword
     */
    List<Books> search(String keyword);
}
