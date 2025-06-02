package com.example.Library_System.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    public List<Author> getAuthorByLastNameContaining(String lastName);

}
