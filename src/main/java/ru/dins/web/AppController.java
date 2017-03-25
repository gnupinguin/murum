package ru.dins.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by key on 25.03.2017.
 */
@Controller
public class AppController {
    @RequestMapping("/create")
    public String createPost(){
        return "create-post.html";
    }

    @RequestMapping("info")
    public String info() { return "info.html"; }
//
//    @RequestMapping("index")
//    public String index() { return "index.html"; }

    @RequestMapping("/edit")
    public String edit() { return "editing.html"; }
}
