package com.kuch.Fooddelivery.api.config;

import com.kuch.Fooddelivery.utils.filter.CustomAuthenticationFilter;
import com.kuch.Fooddelivery.utils.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

/**
 * @author Artur Kuch
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Profile(value = {"development", "production"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/login/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v1/order/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/api/v1/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/v1/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/order/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/inventory-items/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
