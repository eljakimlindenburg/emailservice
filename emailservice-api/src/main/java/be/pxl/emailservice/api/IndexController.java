package be.pxl.emailservice.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Emailservice is up and running voor het juryexamen.";
    }

}
