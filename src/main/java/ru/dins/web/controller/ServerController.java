package ru.dins.web.controller;

import com.datastax.driver.core.utils.UUIDs;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dins.web.model.Like;
import ru.dins.web.model.Post;
import ru.dins.web.persistance.MurumRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@Controller
public class ServerController {

    private Calendar calendar = Calendar.getInstance();

    @Autowired
    private MurumRepository repository;

    @RequestMapping("/")
    public String login(){
        return "start-page.html";
    }

    @RequestMapping("/create")
    public String create(){return "create-post.html";}

    @RequestMapping(value = "/add", params = {"author", "text", }, method = RequestMethod.POST)
    public String add(String author, String text){
        Date creationTime = new Date(calendar.getTime().getTime());
        UUID id = UUIDs.timeBased();
        repository.insertPost(new Post(id, author, creationTime, creationTime, 0, text));
        repository.insertLike(new Like(id, 0));

        return "redirect:/home";
    }

    @RequestMapping("/about")
    public String about() { return "about-us.html"; }

    @RequestMapping("/home")
    public String home() { return "home-page.html"; }

    @RequestMapping("/edit")
    public String edit() { return "update-post.html"; }

    @RequestMapping(value = "/update", params = {"id", "text"})
    public String updatePost(String id, String text) {
        Date modificationTime = new Date(calendar.getTime().getTime());
        UUID postId = UUID.fromString(id);

        Post post = repository.findPostById(postId);
        post.setModificationTime(modificationTime);
        post.setText(text);
        repository.updatePost(post);

        return "redirect:/home";
    }

}

