package com.learn.config;

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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/home","/login","/register").permitAll()
                // .antMatchers(HttpMethod.GET,"/public/**").permitAll()
                .antMatchers(HttpMethod.GET, "/public/**").hasRole("NORMAL")
                .antMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("john")
                //.password("don")
                .password(this.passwordEncoder().encode("don"))
                .roles("NORMAL");
        auth.inMemoryAuthentication()
                .withUser("admin")
                //.password("rock")
                .password(this.passwordEncoder().encode("admin"))
                .roles("ADMIN");
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
