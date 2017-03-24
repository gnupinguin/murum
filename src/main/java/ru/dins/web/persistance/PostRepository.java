package ru.dins.web.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dins.web.model.Post;

import java.util.UUID;

/**
 * Created by gnupinguin on 24.03.17.
 */
@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {
}
