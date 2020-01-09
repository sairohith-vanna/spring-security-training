package com.experimental.security.configurations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       if(authentication.getName().equals(username) && authentication.getCredentials().equals(password)) {
           return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
       }
       else
		    throw new BadCredentialsException("Incorrect credentials supplied.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

    @Value("${default.username}")
    private String username;

    @Value("${default.password}")
    private String password;
}


