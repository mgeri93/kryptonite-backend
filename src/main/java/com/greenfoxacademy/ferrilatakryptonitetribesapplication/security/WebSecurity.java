package com.greenfoxacademy.ferrilatakryptonitetribesapplication.security;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.UserDetailsServiceImpl;

//import static com.greenfoxacademy.ferrilatakryptonitetribesapplication.security.SecurityConstants.LOGIN_URL;
import static com.greenfoxacademy.ferrilatakryptonitetribesapplication.security.SecurityConstants.REGISTER_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private RestAccessDeniedHandler accessDeniedHandler;
  private UserDetailsServiceImpl userDetailsServiceImpl;
  private BCryptPasswordEncoder passwordEncoder;
  private RestAuthenticationEntryPoint unauthorizedHandler;

  public WebSecurity(UserDetailsServiceImpl userDetailsServiceImpl,
      BCryptPasswordEncoder passwordEncoder, RestAccessDeniedHandler accessDeniedHandler,
      RestAuthenticationEntryPoint unauthorizedHandler) {
    this.userDetailsServiceImpl = userDetailsServiceImpl;
    this.passwordEncoder = passwordEncoder;
    this.accessDeniedHandler = accessDeniedHandler;
    this.unauthorizedHandler = unauthorizedHandler;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(unauthorizedHandler)
        .and()
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))
        .addFilter(new JwtAuthorizationFilter(authenticationManager()))
        .authorizeRequests()

        .antMatchers(HttpMethod.POST, REGISTER_URL).permitAll()
        //.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
        .anyRequest()
        .authenticated()
        .and()
        /*.formLogin()
        .loginPage("/login.html")
        .loginProcessingUrl("/api/login")*/

        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.NEVER);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
