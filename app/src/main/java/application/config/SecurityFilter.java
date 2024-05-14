package application.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import application.service.AppUserDetailsService;
import application.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain) throws ServletException, IOException{
            
        String token = getToken(request);

        if(token != null){
            
        }

        //System.out.println(token);

        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null){
            return authHeader.replace("Bearer", "");
        }

        return null;
    }
}