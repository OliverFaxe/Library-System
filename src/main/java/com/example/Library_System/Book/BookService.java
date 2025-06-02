package com.example.Library_System.Book;

import com.example.Library_System.Author.Author;
import com.example.Library_System.Author.AuthorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBookByTitleContaining(String title){
        return bookRepository.getBookByTitleContaining(title);
    }

    public Book postBook(Book newBook){
        return bookRepository.save(newBook);
    }

    public BookDTO mapToBookDTO(Book book){
        return new BookDTO(book);
    }

    public BookWithDetailsDTO mapToBookWithDetailsDTO(Book book) {
        Author author = book.getAuthor();
        AuthorDTO authorDTO = new AuthorDTO(author);

        return new BookWithDetailsDTO(book.getBookId(), book.getTitle(), book.getPublicationYear(), authorDTO);
    }


}
