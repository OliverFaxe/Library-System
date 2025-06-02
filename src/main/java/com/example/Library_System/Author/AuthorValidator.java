package com.example.Library_System.Author;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AuthorValidator {

    private final AuthorRepository authorRepository;

    public AuthorValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> validateAuthorLastNameExists(String lastName){
        List<Author> authors = authorRepository.getAuthorByLastNameContaining(lastName);
        if (authors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No author found with lastname: "+ lastName);
        }
        return authors;
    }

    public void validateNewAuthor(Author author) {
        if (author.getFirstName() == null || author.getFirstName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }

        if (author.getLastName() == null || author.getLastName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required");
        }

        if (author.getBirthYear() < 1500 || author.getBirthYear() > java.time.Year.now().getValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Birth year must be between 1500 and current year");
        }

        if (author.getNationality() == null || author.getNationality().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nationality is required");
        }
    }

}
