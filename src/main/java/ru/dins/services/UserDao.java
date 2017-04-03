package ru.dins.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dins.CassandraRepository.CassandraRepository;

import java.util.Optional;

/**
 * Created by key on 23.03.2017.
 */
@Component
public class UserDao  {
    @Autowired
    private CassandraRepository repository;


    public Optional<User> findByUsername(@NonNull String username){
        System.err.println("findByusername "+username);
        return Optional.ofNullable(repository.findOneByName(username));
    }
    public Optional<User> findByUserID(@NonNull String username){//needed to be changed
        System.err.println("findByusername"+username);
        return Optional.ofNullable(repository.findOneByName(username));
    }

    public void save(@NonNull User user) {
        System.err.println("save in repository " + user.getUsername());
        repository.insert(user);
    }
}
