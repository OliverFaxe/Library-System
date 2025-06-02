package com.example.Library_System.Book;

public class BookTitleDTO {
    private String title;
    private int publicationYear;

    public BookTitleDTO(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
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
