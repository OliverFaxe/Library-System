package com.example.Library_System.Book;

import com.example.Library_System.Author.Author;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class BookValidator {

    private final BookRepository bookRepository;

    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void ifNoResultFound(String title) {
        List<Book> books = bookRepository.getBookByTitleContaining(title);

        if (books.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No books found with title: " + title + " "
            );
        }

    }

    public void validateNewBook(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
        }

        Integer year = book.getPublicationYear();

        if (year == null || year < 1515 || year > java.time.Year.now().getValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Publication year should be atleast 1515 and cannot be more than the current year");
        }

        Integer total = book.getTotalCopies();

        if (total == null || total < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Total copies must be atleast 1");
        }

        Integer available = book.getAvailableCopies();

        if (available == null || available < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Available copies must be atleast 1");
        }

        Author author = book.getAuthor();

        if (author == null || author.getAuthorId() == null || author.getAuthorId() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author object must include a valid authorId only");
        }
    }

}
