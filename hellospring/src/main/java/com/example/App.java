package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
  
   public static void main(String[] args) {

      ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      HelloWorld obj = (HelloWorld) context.getBean("helloWorld", HelloWorld.class);
      obj.setMessage("Hello Spring!");
      obj.getMessage();

      GreetingService greetingService = context.getBean(GreetingService.class);
      greetingService.greet();

     
   }
}