package com.epam.starbun.todolist.controller;


import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/welcome")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "welcome";
    }

    @GetMapping("/")
    public String index() {
        log.info("test");
        return "users";
    }

//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    public String hello(Model model, @RequestParam(defaultValue = "") String id) {
//        Optional<User> user = userRepository.findById(UUID.fromString(id));
//        model.addAttribute("users", user.get());
//        model.addAttribute("lastSearch", id);
//        return "users";
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("user") User user) {
        userRepository.save(user);
        model.addAttribute("users", user);
        return "users";
    }

}