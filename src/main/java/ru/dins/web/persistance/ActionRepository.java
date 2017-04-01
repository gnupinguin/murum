package ru.dins.web.persistance;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dins.web.model.Action;

import java.util.List;
import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
@Repository
public interface ActionRepository extends CassandraRepository<Action> {
    @Query("SELECT * FROM action WHERE user_id=?0 AND post_id=?1")
    List<Action> findByUserPost(UUID userId, UUID postId);

    @Query("SELECT * FROM action WHERE id=?0")
    Action findAction(UUID actionId);

    @Query("SELECT * FROM action LIMIT ?0")
    List<Action> findAll(int n);

    @Query("SELECT * FROM action WHERE user_id=?0 LIMIT ?1")
    List<Action> findAll(UUID userId, int n);

    @Query("SELECT * FROM action WHERE user_id=?0")
    List<Action> findAll(UUID userId);
}
