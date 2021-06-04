package com.example.springbootrestfulservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  //default constructor
public class HelloWorldBean {
    private String message;

}
