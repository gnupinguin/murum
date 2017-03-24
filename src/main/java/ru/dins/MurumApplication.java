package ru.dins;


import com.datastax.driver.core.utils.UUIDs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dins.web.model.Action;
import ru.dins.web.model.ActionType;
import ru.dins.web.persistance.ActionRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@SpringBootApplication
public class MurumApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MurumApplication.class, args);
//		UUID id = UUIDs.timeBased();
//		Calendar calendar = Calendar.getInstance();
//		Date date = new Date(calendar.getTimeInMillis());
//		context.getBean(ActionRepository.class).save(new Action(id, id, ActionType.LIKE.toString(), date));
	}

}
