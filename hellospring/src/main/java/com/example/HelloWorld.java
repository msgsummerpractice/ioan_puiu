package com.example;

public class HelloWorld {      
  
  HelloWorld() {
    System.out.println("HelloWorld constructor called");
  }

  private String message;
  
   // GetterSetter for variable
  public void setMessage(String message){      
    this.message = message;
  }
  
  public void getMessage(){
    System.out.println("Message : " + message);
  }
}