package com.rubenlg94.subscriptionservice.components;

import com.rubenlg94.subscriptionservice.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationApiKeyFilter extends OncePerRequestFilter {

    @Value("${api-key.header}")
    private String header;

    @Value("${api-key.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apikey = request.getHeader(header);
        if (apikey == null || !apikey.equals(secret)) {
            try {
                throw new UnauthorizedException("API-KEY Incorrecta");
            } catch (UnauthorizedException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }

}
