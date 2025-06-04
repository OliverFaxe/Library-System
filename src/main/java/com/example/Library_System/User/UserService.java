package com.example.Library_System.User;

import com.example.Library_System.Book.Book;
import com.example.Library_System.Book.BookDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;


    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this:: mapToUserDTO)
                .toList();
    }

    public UserDTO mapToUserDTO(User user){
        return new UserDTO(user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }

    public UserByEmailDTO mapToUserByEmailDTO(User user){
        return new UserByEmailDTO(user.getFirstName(), user.getRegistrationDate(), user.getEmail(), user.getLastName());
    }

    public List<User> findUserByEmailContaining(String email) {
        userValidator.anyUserWithThatEmail(email);
        return userRepository.findUserByEmailContaining(email);
    }

    public User createUser(UserCreateDTO dto){
        userValidator.validateCreateUser(dto);
        User user = new User();
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setRegistrationDate(new Date());
        return userRepository.save(user);
    }

}
