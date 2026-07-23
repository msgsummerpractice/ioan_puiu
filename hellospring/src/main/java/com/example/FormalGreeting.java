package com.example;

import org.springframework.stereotype.Component;

@Component("formalGreeting")
public class FormalGreeting implements Greeting {

    @Override
    public String greet() {
        return "Good day!";
    }

}
