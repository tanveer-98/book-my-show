package com.tanveer.bookmyshow.Controllers;


import com.tanveer.bookmyshow.Dto.AuthenticationResponseDto;
import com.tanveer.bookmyshow.Dto.LoginRequestDto;
import com.tanveer.bookmyshow.Dto.RegisterRequestDto;
import com.tanveer.bookmyshow.Dto.UserResponseDto;
import com.tanveer.bookmyshow.Entity.User;
import com.tanveer.bookmyshow.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(
            @RequestBody RegisterRequestDto requestBody
    ){
        AuthenticationResponseDto authenticationResponse =  authenticationService.register(requestBody);

        ResponseCookie cookie = ResponseCookie.from("accessToken" , authenticationResponse.accessToken())
                .httpOnly(true)
                .secure(false) // set to true for production for https
                .path("/") // Available for the entire domain
                .maxAge(3600)
                .sameSite("Lax")
                .build();


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE , cookie.toString())
                .build();


    }


    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(
            @RequestBody LoginRequestDto requestBody
            ){
        // store the response

        AuthenticationResponseDto authenticationResponse = authenticationService.login(requestBody);

        // store it in http only cookie

        ResponseCookie cookie = ResponseCookie.from("accessToken" , authenticationResponse.accessToken())
                .httpOnly(true)
                .secure(false) // set to true for production for https
                .path("/") // Availa ble for the entire domain
                .maxAge(3600)
                .sameSite("Lax")
                .build();


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE , cookie.toString())
                .build();

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
