package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.UserBook;

import java.util.List;

public interface UserBookService {

    List<UserBook> ShowUserBooks();

    void AddSentiment(UserBook userbook, int bookId);

    UserBook ShowUserBook(int bookId);
}
