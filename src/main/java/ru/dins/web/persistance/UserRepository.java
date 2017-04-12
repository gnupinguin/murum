package ru.dins.web.persistance;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.dins.web.model.User;

import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
public interface UserRepository extends CrudRepository<User, UUID> {
    @Query("SELECT * FROM user WHERE email=?0")
    User findByEmail(String email);
}
