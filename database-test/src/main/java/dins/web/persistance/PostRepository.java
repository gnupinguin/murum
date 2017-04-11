package dins.web.persistance;

import dins.web.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Created by gnupinguin on 24.03.17.
 */
@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {
}
