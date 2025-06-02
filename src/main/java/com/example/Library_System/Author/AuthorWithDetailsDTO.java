package com.example.Library_System.Author;

import com.example.Library_System.Book.Book;

import java.util.List;

public class AuthorWithDetailsDTO {
    private String firstName;
    private String lastName;
    private int birthYear;
    private String nationality;
    private List<Book> books;

    public AuthorWithDetailsDTO(String firstName, String lastName, int birthYear, String nationality, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.nationality = nationality;
        this.books = books;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
