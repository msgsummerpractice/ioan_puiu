package com.example;

import org.springframework.stereotype.Component;

@Component("casualGreeting")
public class CasualGreeting implements Greeting {

    @Override
    public String greet() {
        return "Hey there!";
    }
}
