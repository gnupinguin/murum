package ru.dins;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.cassandra.core.CassandraTemplate;

@SpringBootApplication
public class MurumApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MurumApplication.class, args);
	}
}
