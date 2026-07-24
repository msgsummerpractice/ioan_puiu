package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {
    private Greeting greeting;


    @Autowired
    public GreetingService(@Qualifier("formalGreeting") Greeting greeting) {
        this.greeting = greeting;
        }

    public void greet() {
        System.out.println(greeting.greet());
    }

}
