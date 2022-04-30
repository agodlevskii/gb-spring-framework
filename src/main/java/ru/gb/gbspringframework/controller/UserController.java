package ru.gb.gbspringframework.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbspringframework.entity.User;
import ru.gb.gbspringframework.service.UserServiceImpl;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userService;

    @GetMapping("users")
    public String users(Model model) {
        final List<User> users = userService.findAll();
        final User newUser = new User();
        model.addAttribute("users", users);
        model.addAttribute("newUser", newUser);
        return "users";
    }
}
