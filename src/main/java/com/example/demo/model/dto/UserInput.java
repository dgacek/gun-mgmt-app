package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInput {
    private Long id;
    private Long roleId;
    private String email;
    private Long phone;
    private String username;
    private String password;
}
