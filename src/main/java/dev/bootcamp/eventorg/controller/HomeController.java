package dev.bootcamp.eventorg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {
    @GetMapping("/")
    public String guest() {
        return "Hello, Guest!";
    }

//    @GetMapping("/admin")
//    public String admin(Principal principal) {
//        return "Hello, " + principal.getName();
//    }
}
