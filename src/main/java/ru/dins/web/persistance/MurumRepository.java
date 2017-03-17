package ru.dins.web.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;
import ru.dins.web.model.Like;
import ru.dins.web.model.Post;

import java.util.List;

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
    public List<Post> findAllPosts(){
        return template.selectAll(Post.class);
    }
    public List<Like> findAllLikes(){
        return template.selectAll(Like.class);
    }
}
