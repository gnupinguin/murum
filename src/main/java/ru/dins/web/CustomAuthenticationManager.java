package ru.dins.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import ru.dins.services.User;
import ru.dins.services.UserDao;


/**
 * Created by key on 26.03.2017.
// */
@Configuration
@Service
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    UserDao userDao;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.err.println("-------------------------------------Authentication");

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userDao.findByUsername(username).orElse(null);
        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        if (!user.isEnabled()) {
            throw new DisabledException("1001");
        }
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}

