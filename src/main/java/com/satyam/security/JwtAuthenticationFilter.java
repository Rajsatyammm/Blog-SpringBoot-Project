package com.satyam.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.satyam.exceptions.CustomException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
            try {
                userName = jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException ige) {
                throw new CustomException("IllegalArgumentException", HttpStatus.UNAUTHORIZED, false);
            } catch (ExpiredJwtException e) {
                throw new CustomException("ExpiredJwtException", HttpStatus.UNAUTHORIZED, false);
            } catch (MalformedJwtException e) {
                throw new CustomException("MalformedJwtException", HttpStatus.UNAUTHORIZED, false);
            } catch (Exception e) {
                throw new CustomException("Exception", HttpStatus.UNAUTHORIZED, false);
            }

            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (userName != null && securityContext.getAuthentication() == null) {
                UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
                boolean isTokenValid = jwtTokenHelper.validateToken(token, userDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(usernamePasswordAuthenticationToken);
                }
                throw new CustomException("User Token not valid", HttpStatus.UNAUTHORIZED, false);
            }
        } else
            throw new CustomException("Unuthorized user", HttpStatus.UNAUTHORIZED, false);

        filterChain.doFilter(request, response);
    }

}
