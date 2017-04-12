package ru.dins;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.ApplicationContext;
import ru.dins.web.model.keys.PostPrimaryKey;
import ru.dins.web.persistance.ActionRepository;
import ru.dins.web.persistance.PostRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@SpringBootApplication
@EnableOAuth2Sso
public class MurumApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MurumApplication.class, args);
		UUID id = UUID.fromString("1936cb10-1450-11e7-a4b4-6758eeaf42a1");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.getTimeInMillis());
//		PostPrimaryKey key = new PostPrimaryKey(date, id);
//		Post post = new Post(key, "Ilja Pavlov", date, 0, "Я получил эту роль!");
//		context.getBean(PostRepository.class).save(post);
//		System.out.println(context.getBean(PostRepository.class).findOne());
//		System.out.println(context.getBean(PostRepository.class).findAll());
//		System.out.println(BasicMapId.id("userId", key));
//		System.out.println(context.getBean(ActionRepository.class).findOne(BasicMapId.id("userId", UUID.fromString("01ba3850-1450-11e7-a164-d35384f14027"))));

		System.out.println(context.getBean(PostRepository.class).findByPostId(UUID.fromString("1936cb10-1450-11e7-a4b4-6758eeaf42a1")));

		System.out.println();
	}

}
