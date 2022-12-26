package com.workflow.workflow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors().disable().csrf().disable().formLogin().loginPage("/login").and().authorizeRequests()
        .antMatchers("/login", "/register", "/assets/**", "/layui/**").permitAll()

        .anyRequest()
        .authenticated().and().exceptionHandling().accessDeniedPage("/login");

    // .and()
    // .formLogin().defaultSuccessUrl("/");

    http.addFilterAt(getUsernamepasswordFilter(),
        UsernamePasswordAuthenticationFilter.class);
  }

  public CusUsernamePasswordAuthenticationFilter getUsernamepasswordFilter() throws Exception {
    return new CusUsernamePasswordAuthenticationFilter(authenticationManager());
  }

}
