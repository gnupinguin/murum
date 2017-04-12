package ru.dins.web.persistance;

import org.springframework.data.repository.CrudRepository;
import ru.dins.web.model.Like;

import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
public interface LikeRepository extends CrudRepository<Like, UUID>{
}
