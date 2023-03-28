package com.climbing_log.security;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
          .cors().and()
          .csrf()
          .disable()
          .authorizeHttpRequests()
          .antMatchers("/api/auth/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/blog/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/climb/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/area/**").permitAll()
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://climbing-log-client.onrender.com"));
      configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS"));
      configuration.setAllowedHeaders(Arrays.asList("*"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/api/**", configuration);
      return source;
    }

}
