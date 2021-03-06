package com.experimental.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultAuthenticationProvider defaultAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/resources/**");

    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                    .antMatchers("/default/auth")
                    .authenticated()
                .and()
                    .authorizeRequests()
                    .antMatchers("/default/name", "/default/test")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(defaultAuthenticationProvider);
        builder.inMemoryAuthentication()
                .withUser("adminm")
                .password(encoder().encode("developer123"))
                .roles("USER");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}