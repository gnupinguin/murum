package ru.dins.web.controller;

import com.datastax.driver.core.utils.UUIDs;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dins.web.model.Like;
import ru.dins.web.model.Post;
import ru.dins.web.persistance.MurumRepository;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by gnupinguin on 22.03.17.
 */
@RestController
@RequestMapping("/rest")
public class RestfulServerController {

    @Autowired
    private MurumRepository repository;

    @RequestMapping(value = "/home-data")
    public List<Pair<Post, Like>> homeData(){
        List<Pair<Post, Like>> res = new LinkedList<>();
        repository.findAllPosts().forEach(new Consumer<Post>() {
            @Override
            public void accept(Post post) {
                res.add(Pair.of(post, repository.findLikeById(post.getId())));
            }
        });
        return res;
    }
    //считаем, что запрос всегда корректный
    @RequestMapping(value = "/like", params = {"id", "action"})
    public void like(String id, boolean action){
        UUID postId = UUID.fromString(id);

        Like like = repository.findLikeById(postId);

        if (action){
            like.increase();
            repository.increaseLike(like);
        } else {
            like.decrease();
            repository.decreaseLike(like);
        }
    }
}
