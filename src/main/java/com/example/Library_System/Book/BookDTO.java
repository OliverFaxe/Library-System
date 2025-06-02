package com.example.Library_System.Book;

public class BookDTO {
    private String title;
    private int publicationYear;

    private String authorFirstName;
    private String authorLastName;

    /*public BookDTO(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }*/

    public BookDTO(Book book) {
        this.title = book.getTitle();
        this.publicationYear = book.getPublicationYear();

        this.authorFirstName = book.getAuthor().getFirstName();
        this.authorLastName = book.getAuthor().getLastName();
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

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
}
