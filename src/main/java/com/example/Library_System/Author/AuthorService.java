package com.example.Library_System.Author;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;

    public AuthorService(AuthorRepository authorRepository, AuthorValidator authorValidator) {
        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorByLastNameContaining(String lastName) {
       return authorValidator.validateAuthorLastNameExists(lastName);
    }

    public Author createAuthor(Author newAuthor) {
        authorValidator.validateNewAuthor(newAuthor);
        return authorRepository.save(newAuthor);
    }

    public AuthorDTO mapToAuthorDTO(Author author){
        return new AuthorDTO(author);
    }
}
