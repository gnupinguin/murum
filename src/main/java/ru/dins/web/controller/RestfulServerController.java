package ru.dins.web.controller;

import static org.springframework.data.cassandra.repository.support.BasicMapId.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dins.web.model.*;
import ru.dins.web.model.keys.ActionPrimaryKey;
import ru.dins.web.model.keys.PostPrimaryKey;
import ru.dins.web.persistance.*;


import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * @author Ilja Pavlov
 */
@RestController
@RequestMapping("/rest")
public class RestfulServerController {
    @Autowired
    protected PostRepository postRepository;

    @Autowired
    protected LikeRepository likeRepository;

    @Autowired
    protected ActionRepository actionRepository;

    @Autowired
    protected UserRepository userRepository;

    @Value("${murum.user.email}")
    private String email;

    @RequestMapping("/posts")
    public Iterable<Post> posts(@RequestParam(required = false) Integer count){
        if (count == null || count <= 0){
            return postRepository.findAll();
        }
        return postRepository.findLastPosts(count);
    }

    @RequestMapping("/user-posts")
    public Iterable<Post> getUserPosts(@RequestParam(required = false) Integer count, OAuth2Authentication oAuth2Authentication){
//        Map<String, String> userData = (Map)oAuth2Authentication.getUserAuthentication().getDetails();
//        String email = userData.get("email");
        User user = userRepository.findByEmail(email);
        if (count != null || count <= 0){
            return postRepository.findByUserId(user.getId());
        }
        return postRepository.findLastUserPosts(user.getId(), count);
    }

    @RequestMapping(value = "/likes", params = {"ids"})
    public Iterable<Like> getLikes(String... ids){
        if (ids == null || ids.length == 0) return null;

        List<UUID> list = new ArrayList<>(ids.length);
        for (String id : ids) {
            list.add(UUID.fromString(id));
        }
        return likeRepository.findAll(list);
    }

    @RequestMapping(value = "/like", params = {"id"})
    public void likeAction(String id){
        Date actionDate = new Date();
        UUID postId = UUID.fromString(id);

//        Map<String, String> userData = (Map)oAuth2Authentication.getUserAuthentication().getDetails();
//        String email = userData.get("email");


        User user = userRepository.findByEmail(email);

        boolean canLike = true;
        List<Action> actions = actionRepository.findByUserPost(user.getId(), postId);
        for (Action action : actions){
            if (action.getType().equals(Action.ActionType.LIKE)){
                canLike = false;
                break;
            }else if (action.getType().equals(Action.ActionType.CANCELLATION_LIKE)){
                break;
            }
        }

        Like like = likeRepository.findOne(postId);

        if (canLike){
            likeRepository.save(like.increase());
            actionRepository.save(new Action(new ActionPrimaryKey(user.getId(), UUID.randomUUID(), actionDate), postId, Action.ActionType.LIKE));
        } else {
            likeRepository.save(like.decrease());
            actionRepository.save(new Action(new ActionPrimaryKey(user.getId(), UUID.randomUUID(), actionDate),  postId, Action.ActionType.CANCELLATION_LIKE));
        }
    }
    @RequestMapping(value = "/add", params = {"content" }, method = RequestMethod.POST)
    public void addPost( String content, OAuth2Authentication oAuth2Authentication){
//        Map<String, String> userData = (Map)oAuth2Authentication.getUserAuthentication().getDetails();
//        String email = userData.get("email");

        User user = userRepository.findByEmail(email);
        Date creationTime = new Date();
        UUID postId = UUID.randomUUID();
        Post post = new Post(new PostPrimaryKey(user.getId(), postId, creationTime), creationTime, 0, content);
        postRepository.save(post);
        likeRepository.save(new Like(postId, 0));

        actionRepository.save(new Action(new ActionPrimaryKey(user.getId(),UUID.randomUUID(), creationTime),  postId,  Action.ActionType.ADD_POST));
    }

    @RequestMapping(value = "/update", params = {"id", "content"})
    public void updatePost(String id, String content, HttpServletResponse response, OAuth2Authentication oAuth2Authentication) {
        Date modificationTime = new Date();
        UUID postId = UUID.fromString(id);

//        Map<String, String> userData = (Map)oAuth2Authentication.getUserAuthentication().getDetails();
//        String email = userData.get("email");

        User user = userRepository.findByEmail(email);
        Post post = postRepository.findByPostId(postId);

        if (post != null && post.getKey().getUserId().equals(user.getId())){
            postRepository.delete(post);
            post.getKey().setModificationTime(modificationTime);
            post.setContent(content);
            post.setVersion(post.getVersion() + 1);
            postRepository.save(post);
            actionRepository.save(new Action(new ActionPrimaryKey(user.getId(), UUID.randomUUID(), modificationTime), postId, Action.ActionType.EDIT_POST));
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/delete", params = {"id"})
    public void deleteAction(String id, HttpServletResponse response, OAuth2Authentication oAuth2Authentication){
        UUID postId = UUID.fromString(id);
//        Map<String, String> userData = (Map)oAuth2Authentication.getUserAuthentication().getDetails();
//        String email = userData.get("email");

        User currentUser = userRepository.findByEmail(email);
        Post post = postRepository.findByPostId(postId);

        if(post != null && post.getKey().getUserId().equals(currentUser.getId())){
            postRepository.delete(post);
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/audit")
    public Iterable<Action> audit(OAuth2Authentication oAuth2Authentication){
//        Map<String, String> userData = (Map)oAuth2Authentication.getUserAuthentication().getDetails();
//        String email = userData.get("email");

        User user = userRepository.findByEmail(email);
        return actionRepository.findAll(user.getId());
    }


}
