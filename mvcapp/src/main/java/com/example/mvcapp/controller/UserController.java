package com.example.mvcapp.controller;

import com.example.mvcapp.model.User;
import com.example.mvcapp.repository.UserRepository;
import com.example.mvcapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showForm(Model model){
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/user")
    public String submitForm(@Valid User user, BindingResult result, @RequestParam(required = false) Long id){
        if (result.hasErrors()){
            return "form";
        }

        if (id!=null){
            userService.updateUser(user.getId(), user);
        }else {
            userService.saveUser(user);
        }

        return "redirect:users";// Redirecionar para a página de lista de usuários
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable Long id, Model model){
        User user = userService.searchUserById(id);
        model.addAttribute("user", user);
        return "form";
    }

//    @PutMapping("/{id}")
//    public String updateUser(@PathVariable Long id, @ModelAttribute User user){
//        userService.updateUser(id, user);
//        return "redirect:users";
//    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
