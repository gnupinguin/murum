package ru.dins.services;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by key on 21.03.2017.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    public UserService(UserDao userRepository) {
        this.userDao = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        System.err.println("In UserDetails");
        User user = userDao.findByUsername(username).orElse(null);
        if (!userDao.findByUsername(username).isPresent()){
            System.err.println("Not find");
        }
        return user;
    }
}
