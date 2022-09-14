package ru.khalilov.project2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/library")
    public String mainPage () {
        return "/main/page";
    }


}
