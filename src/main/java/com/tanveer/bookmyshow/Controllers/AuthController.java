package com.tanveer.bookmyshow.Controllers;


import com.tanveer.bookmyshow.Dto.AuthenticationResponseDto;
import com.tanveer.bookmyshow.Dto.LoginRequestDto;
import com.tanveer.bookmyshow.Dto.RegisterRequestDto;
import com.tanveer.bookmyshow.Dto.UserResponseDto;
import com.tanveer.bookmyshow.Entity.User;
import com.tanveer.bookmyshow.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponseDto createUser(
            @RequestBody RegisterRequestDto requestBody
    ){
        return authenticationService.register(requestBody);
    }


    @PostMapping("/login")
    public AuthenticationResponseDto loginUser(
            @RequestBody LoginRequestDto requestBody
            ){
        return authenticationService.login(requestBody);
    }

    @GetMapping("/me")
    public UserResponseDto getCurrentAuthenticated(Authentication authentication){

        User user = (User) authentication.getPrincipal();

        return
        new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
