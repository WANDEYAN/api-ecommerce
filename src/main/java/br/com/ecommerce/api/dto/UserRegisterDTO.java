package br.com.ecommerce.api.dto;

import lombok.Getter;

@Getter
public class UserRegisterDTO {

    private String name;
    private String email;
    private String password;
}