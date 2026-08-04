package com.example.DBdemo.dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@EqualsAndHashCode
public class UserResponse {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    

}
