package ru.dins.web.persistance;

import com.datastax.driver.core.querybuilder.Ordering;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;
import ru.dins.web.model.Like;
import ru.dins.web.model.Post;

import java.util.List;
import java.util.UUID;

/**
 * Created by gnupinguin on 17.03.17.
 */
@Repository
public class MurumRepository {
    @Autowired
    private CassandraTemplate template;

    public void insertPost(Post post){
        template.insert(post);
    }
    public void insertLike(Like like){
        template.insert(like);
    }

    /*Необходимо, чтобы modificationTime был помечен как ключ, чтобы всё сразу было отсортировано*/
    public List<Post> findAllPosts(){
        return template.selectAll(Post.class);
    }
    public List<Like> findAllLikes(){
        return template.selectAll(Like.class);
    }

    public void decreaseLike(Like like){
        template.update(like);
    }
    public void increaseLike(Like like){
        template.update(like);
    }


    public Like findLikeById(UUID id){
        return template.selectOneById(Like.class, id);
    }
    public Post findPostById(UUID id){
        return template.selectOneById(Post.class, id);
    }

    public void updatePost(Post post){
        template.update(post);
    }

}
