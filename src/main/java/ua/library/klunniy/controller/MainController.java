package ua.library.klunniy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Serhii Klunniy
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String sayHello() {
        return "index";
    }

}
