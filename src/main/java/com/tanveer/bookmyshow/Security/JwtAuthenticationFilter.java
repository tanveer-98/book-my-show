package com.tanveer.bookmyshow.Security;


import com.tanveer.bookmyshow.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get the authHeader
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.contains("Bearer")){
            // then allow normally
            filterChain.doFilter(request , response);
            return;
        }

        // extract the jwt token

        String jwtToken = authHeader.substring(7); // because "Bearer " has 7 characters

        String userName = jwtService.extractUsername(jwtToken); // this will return the sub : email

        if( userName != null &&  SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails   = userDetailsService.loadUserByUsername(userName);

                // if user is found i.e validated

                if(jwtService.isTokenValid(jwtToken , userDetails )){

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null ,
                            userDetails.getAuthorities()
                    );
                    //

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    ); /// this sets few meta data on who makes the request

                    // next set the user authenticated to the security context so that other controllers can directly access the user details from the authentication object

                    SecurityContextHolder.getContext().setAuthentication(authToken);
        }

            // next continue with the reqeust flow

            filterChain.doFilter(request, response);
        }

    }
}
