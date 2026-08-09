package com.tanveer.bookmyshow.Service;

import com.tanveer.bookmyshow.Dto.AuthenticationResponseDto;
import com.tanveer.bookmyshow.Dto.LoginRequestDto;
import com.tanveer.bookmyshow.Dto.RegisterRequestDto;

public interface AuthenticationService {
    AuthenticationResponseDto register(RegisterRequestDto request);
    AuthenticationResponseDto login(LoginRequestDto request);
}
