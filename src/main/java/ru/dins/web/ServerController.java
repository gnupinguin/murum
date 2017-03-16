//package ru.dins.web;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import static ru.dins.LoggersMessageStore.*;
//
//import ru.dins.kafka.producer.QuoteProducer;
//import ru.dins.kafka.producer.UnsentQuoteException;
//import ru.dins.web.persistence.QuoteRepository;
//import ru.dins.web.model.quote.Quote;
//
//import java.net.ConnectException;
//import java.util.List;
//
//
//@Controller @Slf4j
//public class ServerController {
//
//
//    @Value("${server.remote-host}")
//    private String remoteHost;
//
//
//    @RequestMapping("/")
//    public String login(){
//        return "public/start-page.html";
//    }
//
//    @RequestMapping("/create")
//    public String createQuote(){
//        return "public/create-post.html";
//    }
//
//    @RequestMapping("/info")
//    public String info() { return "public/info.html"; }
//
//    @RequestMapping("/index")
//    public String index() { return "public/index.html"; }
//
//    @RequestMapping("/edit")
//    public String edit() { return "public/editing.html"; }
//
//
//
//    @RequestMapping(value="/")
//    public String start(Model model){
//        try {
//            return "start-page";
//        }
//        } catch (ConnectException e){
//            log.error(e.getMessage());
//        }
//    }
//
//
//    @RequestMapping(value = "/create")
//    public String showQuotes(Model model) {
//        try{
//            return "create-post";
//        } catch (ConnectException e){
//            log.error(e.getMessage());
//        }
//    }
//
//}
