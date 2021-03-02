package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${welcome.message}")
    private String hi;

    /*
        public WelcomeController (@Value("${welcome.message}") String message) {
            this.hi = message;
        }
    */
    @GetMapping("/")
    public String sayHello() {
        return hi;
    }
}