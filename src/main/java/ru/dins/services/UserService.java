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
//
//    @PostConstruct
//    public void init() throws UsernameNotFoundException {
//        if (!userDao.findByUserName("user").isPresent()){
//            userDao.save(User.builder()
//                    .username("user")
//                    .password("1234")
//                    .authorities(ImmutableList.of(Role.USER))
//                    .accountNonExpired(true)
//                    .accountNonLocked(true)
//                    .credentialsNonExpired(true)
//                    .enabled(true)
//                    .build());
//        }
//
//    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        System.err.println("In UserDetails");
//        User newUser = User.builder()
//                .id(UUID.randomUUID())
//                .username(username)
//                .password("123456")
//                .authorities(ImmutableList.of(Role.USER))
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .credentialsNonExpired(true)
//                .enabled(true)
//                .build();

//        if (!userDao.findByUserName(username).isPresent()){
//            System.err.println("Not find");
////            userDao.save(newUser);
//        }
//        return userDao.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("not found user"));
//        return newUser;

        return User.builder()
                .username(username)
                .password("1234")
                .authorities(ImmutableList.of(Role.USER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
