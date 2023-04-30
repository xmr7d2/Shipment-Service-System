package com.restful.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipper {
    private Long id;
    private String no;
    private String name;
    private String password;
    private int age;
    private String phone;
}

