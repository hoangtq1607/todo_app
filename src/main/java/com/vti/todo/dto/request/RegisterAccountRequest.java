package com.vti.todo.dto.request;

import com.vti.todo.validation.EmailNotUnique;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterAccountRequest {

    @NotNull
    @Email
    @EmailNotUnique
    private String email; //check ton tai trong db

    @NotNull
    @Size(min = 3, max = 15)
    private String password;

    @NotNull
    private String fullName;

}
