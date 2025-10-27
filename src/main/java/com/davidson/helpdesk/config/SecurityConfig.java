package com.davidson.helpdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.davidson.helpdesk.security.JWTUtil;
import com.davidson.helpdesk.security.JwtAuthenticationFilter;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] PUBLIC_MATCHERS = {
          "/h2-console/**"
  };

  private final Environment env;
  private final JWTUtil jwtUtil;
  private final UserDetailsService userDetailsService;

  public SecurityConfig(Environment env, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
    this.env = env;
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    if(Arrays.asList(env.getActiveProfiles()).contains("test")){
      http.headers().frameOptions().disable();
    }

    http.cors().and().csrf().disable();
    http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
    http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
                    .anyRequest().authenticated();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
    CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
  }
}
