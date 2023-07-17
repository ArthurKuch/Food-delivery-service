package com.kuch.Fooddelivery.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Artur Kuch
 */
public class TokenUtil {

    public static String accessToken(HttpServletRequest request, Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String refreshToken(HttpServletRequest request, Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
    }

}
