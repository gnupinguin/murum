package ru.dins.web.controller;

import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dins.web.model.Action;
import ru.dins.web.model.Like;
import ru.dins.web.model.Post;


import java.util.*;

/**
 * Created by gnupinguin on 22.03.17.
 */
@RestController
@RequestMapping("/rest")
public class RestfulServerController extends BaseController {

    @RequestMapping(value = "/home-data")
    public List<Pair<Post, Like>> homeData(){
        List<Pair<Post, Like>> res = new LinkedList<>();

        for(Post post : postRepository.findAll()){
            res.add(Pair.of(post, likeRepository.findOne(post.getId())));
        }
        return res;
    }

    //считаем, что запрос всегда корректный
    @RequestMapping(value = "/like", params = {"id", "action", "author"})
    public void like(String id, String author, boolean action){
        Date actionDate = new Date(calendar.getTimeInMillis());
        UUID postId = UUID.fromString(id);

        Like like = likeRepository.findOne(postId);

        if (action){
            likeRepository.save(like.increase());
            actionRepository.addLikeAction(postId, author, actionDate);
        } else {
            likeRepository.save(like.decrease());
            actionRepository.addCancellationLikeAction(postId, author, actionDate);
        }
    }

    @RequestMapping(value = "/audit")
    public List<Action> audit(@RequestParam(value = "id", required = false) String id){
        if (id != null) return actionRepository.getUserHistory("gnupinguin");
        return actionRepository.getHistory();
    }

    @RequestMapping(value = "/like-info", params = {"id"})
    public int likeInfo(String id){
        UUID postId = UUID.fromString(id);
        return likeRepository.findOne(postId).getCounter();
    }

}
