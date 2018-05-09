package com.smash.com.smash.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
//@EnableResourceServer
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private MemberDetailsService memberDetailsService;

//    @Autowired
//    public ResourceServerConfig(AuthenticationManager authenticationManager,
//                                MemberDetailsService customUserDetailsService) {
//        this.authenticationManager = authenticationManager;
//        this.MemberDetailsService = customUserDetailsService;
//    }

    @Override
    @Order(-20)
    protected void configure(HttpSecurity http) throws Exception{
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers().antMatchers("/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests().anyRequest().authenticated()
//                .authorizeRequests().anyRequest().permitAll()
                ;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager)
//        .userDetailsService(memberDetailsService)
        ;
    }
}
