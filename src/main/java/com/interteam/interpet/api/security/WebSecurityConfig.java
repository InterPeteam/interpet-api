package com.interteam.interpet.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/login",
            "/register"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    //TODO podzielić role na odpowiednie endpointy
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/offers/**").hasAnyAuthority("ROLE_OWNER", "ROLE_PROTECTOR")
                .antMatchers("/users/**").hasAnyAuthority("ROLE_OWNER", "ROLE_PROTECTOR")
                .and().addFilter(new JwtFilter(authenticationManager()))
                .csrf().disable();
    }
}
