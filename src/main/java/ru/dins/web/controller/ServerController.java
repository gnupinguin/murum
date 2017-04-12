package ru.dins.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ilja Pavlov
 */
@Controller
public class ServerController {

    @RequestMapping("/")
    public String login(){
        return "start-page.html";
    }

    @RequestMapping("/create")
    public String create(){return "create-post.html";}

    @RequestMapping("/about")
    public String about() { return "about-us.html"; }

    @RequestMapping("/home")
    public String home() { return "home-page.html"; }

    @RequestMapping("/edit")
    public String edit() { return "update-post.html"; }


}

