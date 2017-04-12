package ru.dins.web.persistance;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dins.web.model.Post;

import java.util.List;
import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
@Repository
public interface PostRepository extends CassandraRepository<Post> {
    @Query("SELECT * FROM post WHERE id=?0")
    Post findByPostId(UUID id);

    @Query("SELECT * FROM post LIMIT ?0")
    List<Post> findLastPosts(int n);

    @Query("SELECT * FROM post WHERE user_id=?0 LIMIT ?1")
    List<Post> findLastUserPosts(UUID userId, int n);

    @Query("SELECT * FROM post WHERE user_id=?0")
    List<Post> findByUserId(UUID userId);


}
