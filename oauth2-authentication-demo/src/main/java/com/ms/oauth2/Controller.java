package com.ms.oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String sayHello(){
        return "This is hello from insecure endpoint";
    }

    @GetMapping("/secured")
    public String sayHelloSecured(){
        return "This is hello from secure endpoint";
    }
}
