package com.tanveer.bookmyshow.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(

        @NotBlank(message = "firstname must be entered")
        String firstName,

        @NotBlank(message = "last Name must be Entered")
        String lastName,

        @Email
        @NotBlank(message = "Email must be Entered")
        String email,

        @NotBlank
        @Size(min = 8 , message = "Password should be of minimum 8 characters")
        String password,

        @Size(min = 10 , max = 10 , message = "Phone number must be of 10 digits")
        String phoneNumber
){}
