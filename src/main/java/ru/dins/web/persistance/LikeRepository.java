package ru.dins.web.persistance;

import org.springframework.data.repository.CrudRepository;
import ru.dins.web.model.Like;

import java.util.UUID;

/**
 * Created by gnupinguin on 24.03.17.
 */
public interface LikeRepository extends CrudRepository<Like, UUID>{
}
