package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Configuration
@ComponentScan(basePackages = "com.example")
public class App {
  
   public static void main(String[] args) {

      ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
      HelloWorld obj = (HelloWorld) context.getBean("helloWorld", HelloWorld.class);
      obj.setMessage("Hello Spring!");
      obj.getMessage();

     
   }
}