package ru.dins.web;

import com.google.common.collect.ImmutableList;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dins.services.Role;
import ru.dins.services.User;
import ru.dins.services.UserDao;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by key on 25.03.2017.
 */
@EnableOAuth2Client
@RestController
@Controller
public class AppController {
    @Autowired
    UserDao userDao;
    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @RequestMapping("/create")
    public String createPost(Authentication principal, ClientResources client)
            throws OAuth2Exception,OAuthSystemException,OAuthProblemException, ServletException, IOException, JSONException {
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);

        String userEmail = template.getAccessToken().getAdditionalInformation().get("email").toString();
        String user_id = template.getAccessToken().getAdditionalInformation().get("user_id").toString();
//        getUserName(user_id);
        if (!userDao.findByUsername(userEmail).isPresent()) {
            userDao.save(User.builder().username(userEmail)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .authorities(ImmutableList.of(Role.USER))
                    .id(UUID.randomUUID())
                    .build());
        }
        return "create-post.html";
    }
    @RequestMapping("/unauthenticated")
    public String logout(){
        return "logout";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    public String getUserName(String title) throws IOException,JSONException {
        URL url = new URL("https://api.vk.com/method/users.get?user_ids=" + title);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        Scanner scanner = new Scanner(url.openStream());
        String response = scanner.useDelimiter("\\Z").next();
        JSONObject json = new JSONObject(response);
        String userName = json.getJSONArray("response").getJSONObject(0).getString("first_name");
        scanner.close();
        return userName;
    }
}