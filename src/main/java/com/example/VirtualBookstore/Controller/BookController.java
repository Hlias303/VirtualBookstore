package com.example.VirtualBookstore.Controller;

import com.example.VirtualBookstore.Config.UserPrincipal;
import com.example.VirtualBookstore.Model.Books;
import com.example.VirtualBookstore.Service.Interface.BookService;
import com.example.VirtualBookstore.Service.Interface.RecommendEngine;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController  
public class BookController {

    private final BookService service;
    private final RecommendEngine rec_service;

    public BookController(BookService service, RecommendEngine rec_service) {
        this.service = service;
        this.rec_service = rec_service;
    }

    @GetMapping("/Books")
    public List<Books> ShowBooks() {
        return service.ShowAllBooks();
    }

    @GetMapping("/Books/{id}")
    public Books GetBookById(@PathVariable String id) {
        return service.ShowBookById(Integer.parseInt(id));
    }

    @PostMapping("/AddBook")
    public void AddBook(@RequestPart Books book) {
        service.AddBook(book);
    }

    @PostMapping("/AddBookImage")
    public String AddBookWithImage(@RequestPart(required = false) Books book, 
                                   @RequestPart MultipartFile image) throws IOException {
        if (book != null && image != null) {
            service.BookAddorUpdate(book, image);
            return "OK";
        }
        return "";
    }

    @GetMapping("/Books/{id}/image")
    public byte[] getImageByBookId(@PathVariable String id) {
        if (id == null || !id.matches("\\d+")) {
            return new byte[0]; // empty image instead of crashing
        }
        Books book = service.GetBookByID(Integer.parseInt(id));
        return book.getImageData();
    }

    @PutMapping("/Books/{id}")
    public void UpdateBook(@PathVariable String id, 
                           @RequestPart(required = false) Books book,
                           @RequestPart MultipartFile image) throws IOException {
        int bookId = Integer.parseInt(id);
        if (book != null) book.setId(bookId);
        service.BookAddorUpdate(book, image);
    }

    @DeleteMapping("/Books/{id}")
    public void DeleteBook(@PathVariable String id, Books book) {
        int bookId = Integer.parseInt(id);
        service.DeleteBook(bookId, book);
    }

    @GetMapping("/Books/search")
    public List<Books> SearchBook(@RequestParam String keyword) {
        return service.search(keyword);
    }

    @PostMapping("/Recommendations")
    public List<Books> getRecommendations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal) {
            int userId = ((UserPrincipal) auth.getPrincipal()).getId(); // Directly use getId() method
            return rec_service.getRecommendationsForUser(userId, 2);
        }
        return List.of();
    }
}
