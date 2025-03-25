package carami.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoWebController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
