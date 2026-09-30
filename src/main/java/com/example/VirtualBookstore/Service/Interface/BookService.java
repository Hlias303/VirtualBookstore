package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.Books;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<Books> ShowAllBooks();

    Books ShowBookById(int id);

    void AddBook(Books book);

    void DeleteBook(int id, Books book);

    void BookAddorUpdate(Books book, MultipartFile image) throws IOException;

    Books GetBookByID(int bookID);

    List<Books> search(String keyword);
}
