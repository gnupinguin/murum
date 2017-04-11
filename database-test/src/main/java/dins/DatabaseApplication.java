package dins;


import com.datastax.driver.core.utils.UUIDs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DatabaseApplication.class, args);
		UUID id = UUIDs.timeBased();
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.getTimeInMillis());
//		context.getBean(ActionRepository.class).addPostAction(new Post(id, "gnupinguin", date, date, 0, "Hello, World!"));
	}

}
