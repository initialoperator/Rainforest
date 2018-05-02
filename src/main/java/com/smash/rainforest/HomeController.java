package com.smash.rainforest;

import hello.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HomeController {
    private final static String template = "hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/home")
    @ResponseBody
    public String sayHello(){
        return "welcome to home";
    }


    @GetMapping("/hello-buddy")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
