package pl.coderslab.diywithspring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", ""})
    public String home() {
        return "/static/home";
    }

    @GetMapping("/about")
    public String about() {
        return "/static/about";
    }

    @GetMapping("user/chat/")
    public String chat() {
        return "/static/chat";
    }
}