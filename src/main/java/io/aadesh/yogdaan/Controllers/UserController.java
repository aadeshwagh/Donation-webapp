package io.aadesh.yogdaan.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/index")
    public String login() {
        return "index";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
