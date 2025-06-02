package com.example.Library_System.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/email/{email}")
    public List<UserByEmailDTO> findUserByEmailContaining(@PathVariable String email){
        return userService.findUserByEmailContaining(email).stream()
                .map(userService :: mapToUserByEmailDTO)
                .toList();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreateDTO dto){
        User savedUser = userService.createUser(dto);
        return userService.mapToUserDTO(savedUser);
    }
}
