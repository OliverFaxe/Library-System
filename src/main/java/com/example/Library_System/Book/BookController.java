package com.example.Library_System.Book;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping // title?title=
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(bookService:: mapToBookDTO)
                .toList();
    }

    @GetMapping("/title")
    public List<BookWithDetailsDTO> getBookByTitleContaining(@RequestParam String title) {
        return bookService.getBookByTitleContaining(title)
                .stream()
                .map(bookService::mapToBookWithDetailsDTO)
                .toList();
    }

    @PostMapping()
    public Book saveBook(@RequestBody Book newBook){
        return bookService.postBook(newBook);
    }

}
