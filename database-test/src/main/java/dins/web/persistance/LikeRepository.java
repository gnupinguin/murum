package dins.web.persistance;

import dins.web.model.Like;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by gnupinguin on 24.03.17.
 */
public interface LikeRepository extends CrudRepository<Like, UUID>{
}
