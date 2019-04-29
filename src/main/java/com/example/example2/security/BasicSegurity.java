package com.example.example2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicSegurity extends WebSecurityConfigurerAdapter {

  private static String REALM = "MY_TEST_REALM";

  @Autowired
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("raul").password(passwordEncoder().encode("Loerftujyo")).roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/user/**").hasRole("ADMIN")
            .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
    return new CustomBasicAuthenticationEntryPoint();
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
