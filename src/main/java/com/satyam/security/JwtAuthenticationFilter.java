package com.satyam.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper tokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
        }
        if (token != null) {
            try {
                username = tokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException iae) {

            } catch (ExpiredJwtException eje) {

            } catch (MalformedJwtException mje) {

            } catch (Exception e) {

            }
        }
    }

}
