package com.tanveer.bookmyshow.Service;


import com.tanveer.bookmyshow.Dto.AuthenticationResponseDto;
import com.tanveer.bookmyshow.Dto.LoginRequestDto;
import com.tanveer.bookmyshow.Dto.RegisterRequestDto;
import com.tanveer.bookmyshow.Entity.User;
import com.tanveer.bookmyshow.Exception.UserAlreadyExistsException;
import com.tanveer.bookmyshow.Exception.UserNameNotFoundException;
import com.tanveer.bookmyshow.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    // inject Classes required for authentication

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;



//    Returns accessToken on successfull registration
    @Override
    public AuthenticationResponseDto register(RegisterRequestDto request) {

        // check if userEmail exists in the database
        if(userRepository.existsByEmail(request.email())){
            throw new UserAlreadyExistsException("The user is Already Registered. Please login");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(User.Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setPhoneNumber(request.phoneNumber());

        userRepository.save(user);

        // After user is saved , generate a Token

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponseDto(jwtToken);
    }

    @Override
    public AuthenticationResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email()).orElseThrow(()-> new
                UserNameNotFoundException("The email is invalid"));

        // generate jwt

        String jwtToken =  jwtService.generateToken(user);

        return new AuthenticationResponseDto(jwtToken);

    }
}
