package com.example.VirtualBookstore.Service.Implementation;

import com.example.VirtualBookstore.Model.Books;
import com.example.VirtualBookstore.Repo.BookRepo;
import com.example.VirtualBookstore.Service.Interface.BookService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo repo;

    public BookServiceImpl(BookRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Books> ShowAllBooks() {
        return repo.findAll(Sort.by("id"));
    }

    @Override
    public Books ShowBookById(int id) {
        return repo.findById(id).orElse(new Books());
    }

    @Override
    public void AddBook(Books book) {
        repo.save(book);
    }

    @Override
    public void DeleteBook(int id, Books book) {
        repo.deleteById(book.getId());
    }

    @Override
    public void BookAddorUpdate(Books book, MultipartFile image) throws IOException {
        book.setImageName(image.getOriginalFilename());
        book.setImageType(image.getContentType());
        book.setImageData(image.getBytes());
        repo.save(book);
    }

    @Override
    public Books GetBookByID(int bookID) {
        return repo.findById(bookID).orElse(new Books());
    }

    @Override
    public List<Books> search(String keyword) {
        return repo.SearchBook(keyword);
    }
}
