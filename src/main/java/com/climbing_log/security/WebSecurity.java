package com.climbing_log.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.climbing_log.enums.Role;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;
  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf()
          .disable()
          .authorizeHttpRequests()
          .antMatchers("/api/auth/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/blog/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/climbs/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/locations/**").permitAll()
          .antMatchers(HttpMethod.POST, "/api/blog/add*").hasAuthority(Role.ADMIN.name())
          .anyRequest()
          .authenticated()
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .authenticationProvider(authenticationProvider)
          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
  
      return http.build();
    }

}
