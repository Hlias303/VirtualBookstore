package com.example.VirtualBookstore.Controller;


import com.example.VirtualBookstore.Model.Books;
import com.example.VirtualBookstore.Model.UserBook;
import com.example.VirtualBookstore.Service.Interface.UserBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserBookController {

    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping("/UserBooks")
    public List<UserBook> getUserBooks() {
        return userBookService.ShowUserBooks();
    }

    @GetMapping("/UserBooks/{id}")
    public UserBook getUserBook(@PathVariable int bookId) {
        return userBookService.ShowUserBook(bookId);
    }

    @PostMapping("/Books/AddSentiment")
    public void AddSentiment(@RequestBody UserBook userbook) {
        userBookService.AddSentiment(userbook, userbook.getBook().getId());
    }
}
