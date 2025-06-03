package com.example.Library_System.Author;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/authors")
@RestController
public class AuthorController {

    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

//    @GetMapping Innan jag använde ResponseEntity<>
//    public List<AuthorDTO> getAllAuthors() {
//        return authorService.getAllAuthors()
//                .stream()
//                .map(authorService :: mapToAuthorDTO)
//                .toList();
//    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors()
                .stream()
                .map(authorService :: mapToAuthorDTO)
                .toList());
    }

    @GetMapping("/lastname") // RequestParam är ?lastName=
    public ResponseEntity<List<AuthorDTO>> getAuthorByLastNameContaining(@RequestParam String lastName) {
        return ResponseEntity.ok(authorService.getAuthorByLastNameContaining(lastName)
                .stream()
                .map(authorService :: mapToAuthorDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author createdAuthor = authorService.createAuthor(author);

        URI location = URI.create("/authors/" + createdAuthor.getAuthorId());

        return ResponseEntity.created(location).body(createdAuthor);
    }

}
