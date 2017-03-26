package ru.dins.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dins.web.model.Like;
import ru.dins.web.model.Post;
import ru.dins.web.persistance.LikeRepository;
import ru.dins.web.persistance.PostRepository;

import java.util.*;

/**
 * Created by gnupinguin on 22.03.17.
 */
@RestController
@RequestMapping("/rest")
public class RestfulServerController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @RequestMapping(value = "/home-data")
    public List<Pair<Post, Like>> homeData(){
        List<Pair<Post, Like>> res = new LinkedList<>();

        for(Post post : postRepository.findAll()){
            res.add(Pair.of(post, likeRepository.findOne(post.getId())));
        }
        return res;
    }

    //считаем, что запрос всегда корректный
    @RequestMapping(value = "/like", params = {"id", "action"})
    public void like(String id, boolean action){
        UUID postId = UUID.fromString(id);
        Like like = likeRepository.findOne(postId);

        if (action){
            likeRepository.save(like.increase());
        } else {
            likeRepository.save(like.decrease());
        }
    }

}
