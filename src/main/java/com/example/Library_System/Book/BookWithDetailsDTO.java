package com.example.Library_System.Book;

import com.example.Library_System.Author.AuthorDTO;

public class BookWithDetailsDTO {
    private Long bookId;
    private String title;
    private int publicationYear;
    private AuthorDTO author;

    public BookWithDetailsDTO(Long bookId, String title, int publicationYear, AuthorDTO author) {
        this.bookId = bookId;
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
