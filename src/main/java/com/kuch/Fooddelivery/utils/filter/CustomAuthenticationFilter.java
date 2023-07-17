package com.kuch.Fooddelivery.utils.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuch.Fooddelivery.utils.token.TokenUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Artur Kuch
 */

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

        private final AuthenticationManager authenticationManager;

        public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
            this.authenticationManager = authenticationManager;
        }

        @SneakyThrows
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            JsonNode rootNode = new ObjectMapper().readTree(request.getInputStream());
            String email = rootNode.get("email").asText();
            String password = rootNode.get("password").asText();

            log.info("Email is: {}, Password is: {}", email, password);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

            return authenticationManager.authenticate(authenticationToken);
        }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String accessToken = TokenUtil.accessToken(request, authentication);
        String refreshToken = TokenUtil.refreshToken(request, authentication);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
