package com.learn.config;

import com.learn.Services.CustomUserdetails;
import com.learn.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                //.antMatchers("/home","/login","/register").permitAll()
                // .antMatchers(HttpMethod.GET,"/public/**").permitAll()
                .antMatchers(HttpMethod.GET, "/public/**").hasRole("NORMAL")
                .antMatchers( "/api/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

   /* @Override   ----comment in last branch---
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                //.password("don")
                .password(this.passwordEncoder().encode("user123"))
                .roles("NORMAL");
        auth.inMemoryAuthentication()
                .withUser("admin")
                //.password("rock")
                .password(this.passwordEncoder().encode("admin"))
                .roles("ADMIN");
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());// Set the password encoder
    }

    /* @Bean
     public PasswordEncoder passwordEncoder(){
         return NoOpPasswordEncoder.getInstance();
     }*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);//The default value is 10.
    }
    //Role-high level overview(Admin has permission to read ,delete)
}
