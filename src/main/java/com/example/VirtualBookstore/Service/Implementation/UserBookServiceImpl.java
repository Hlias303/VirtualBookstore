package com.example.VirtualBookstore.Service.Implementation;

import com.example.VirtualBookstore.Model.Books;
import com.example.VirtualBookstore.Model.User;
import com.example.VirtualBookstore.Model.UserBook;
import com.example.VirtualBookstore.Repo.BookRepo;
import com.example.VirtualBookstore.Repo.UserBookRepo;
import com.example.VirtualBookstore.Repo.UserRepo;
import com.example.VirtualBookstore.Service.Interface.UserBookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookServiceImpl implements UserBookService {

    private final UserBookRepo repo;
    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    public UserBookServiceImpl(UserBookRepo repo, UserRepo userRepo, BookRepo bookRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public List<UserBook> ShowUserBooks() {
        return repo.findAll();
    }

    @Override
    public void AddSentiment(UserBook userbook, int bookId) {
        Books book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // We use Authentication to get the current user object that want to review
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepo.findByUsername(name);

        userbook.setBook(book);
        userbook.setUser(user);
        repo.save(userbook);
    }

    @Override
    public UserBook ShowUserBook(int bookId) {
        // We use Authentication to get the current user object that want to review
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepo.findByUsername(name);
        int UserId = user.getId();

        return repo.findByUserIdAndBookId(UserId, bookId)
                .orElseThrow(() -> new RuntimeException("User With rating This book has not been found"));
    }
}
