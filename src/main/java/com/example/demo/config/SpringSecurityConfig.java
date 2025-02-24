package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
    public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

        // Create 2 users for demo
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.inMemoryAuthentication()
                    .withUser("user").password("{noop}password").roles("USER")
                    .and()
                    .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

        }

        // Secure the endpoins with HTTP Basic authentication
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    //HTTP Basic authentication
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/customer-accounts/**").hasRole("USER")
                    .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                    .antMatchers("/h2-console/**").permitAll()
                    .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .headers().disable(); //h2 console
        }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    }