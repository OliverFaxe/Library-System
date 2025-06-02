package com.example.Library_System.Author;

import com.example.Library_System.Book.Book;
import com.example.Library_System.Book.BookTitleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorDTO {
    private String firstName;
    private String lastName;
    private int birthYear;
    private String nationality;
    private List<BookTitleDTO> books;

    public AuthorDTO(Author author) {
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.birthYear = author.getBirthYear();
        this.nationality = author.getNationality();
        this.books = author.getBooks().stream()
                .map(book -> new BookTitleDTO(book.getTitle(), book.getPublicationYear()))
                .collect(Collectors.toList());

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<BookTitleDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookTitleDTO> books) {
        this.books = books;
    }
}
