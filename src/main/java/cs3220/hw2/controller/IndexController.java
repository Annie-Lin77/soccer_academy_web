package cs3220.hw2.controller;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class IndexController {

    @RequestMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/home")
    public String Home() {
        return "Home";
    }

}
