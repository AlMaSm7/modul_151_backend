package ch.bbw.pokeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
    @GetMapping("/thegreet")
    public String helloWorld(){
        return "hello";
    }
}
