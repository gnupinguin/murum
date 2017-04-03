package ru.dins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MurumApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MurumApplication.class, args);
	}
}
