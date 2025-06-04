package com.example.Library_System.User;

import com.example.Library_System.Author.Author;
import com.example.Library_System.Book.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class UserValidator {

    UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void anyUserWithThatEmail(String email) {
        List<User> users = userRepository.findUserByEmailContaining(email);

        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " No user with this email: " + email);
        }
    }

    public void validateCreateUser(UserCreateDTO user) {
        if (user.getFirstName() == null || user.getFirstName().isBlank() || !user.getFirstName().matches("^[A-Za-z]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid first name is required");
        }

        if (user.getLastName() == null || user.getLastName().isBlank() || !user.getLastName().matches("^[A-Za-z]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid last name is required");
        }

        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid email is required");
        }

        if (user.getPassword() == null || user.getPassword().isBlank() || user.getPassword().length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters/numbers");
        }
    }
}
