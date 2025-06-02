package com.example.Library_System.Author;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/authors")
@RestController
public class AuthorController {

    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .map(authorService :: mapToAuthorDTO)
                .toList();
    }

    @GetMapping("/lastname") // RequestParam är ?lastName=
    public List<AuthorDTO> getAuthorByLastNameContaining(@RequestParam String lastName) {
        return authorService.getAuthorByLastNameContaining(lastName)
                .stream()
                .map(authorService :: mapToAuthorDTO)
                .toList();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author newAuthor){
        return authorService.createAuthor(newAuthor);
    }

}
