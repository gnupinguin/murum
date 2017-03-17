package ru.dins;


import com.datastax.driver.core.utils.UUIDs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dins.web.model.Post;
import ru.dins.web.persistance.MurumRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class MurumApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MurumApplication.class, args);
//		Calendar calendar = Calendar.getInstance();
//		Date creationTime = new Date(calendar.getTime().getTime());
//		MurumRepository repository = context.getBean(MurumRepository.class);
//		repository.insertPost(new Post(UUIDs.timeBased(), "gnupinguin", creationTime, creationTime, 0, "Hello, World!"));
//		System.out.println(repository.findAllPosts());

	}

}
