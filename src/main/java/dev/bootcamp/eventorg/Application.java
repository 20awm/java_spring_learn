package dev.bootcamp.eventorg;

import dev.bootcamp.eventorg.config.RsaKeyProperties;
import dev.bootcamp.eventorg.user.User;
import dev.bootcamp.eventorg.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class Application {

	private static final Logger log =
			LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		log.info("Application running!");

//		var helloWorld = new HelloWorld();
//		System.out.println(helloWorld.sayHelloWorld());
	}
//	@Bean
//	CommandLineRunner runner(UserRestClient client) {
//		return args -> {
//			List<User> users = client.findAll();
//			System.out.println(users);
//
//			User user = client.findById(1);
//			System.out.println(user);
//		};
//	}
//	@Bean
//	CommandLineRunner runner(
//	) {
//		return args -> {
//			Event event = new Event(1,"Konser A",
//					LocalDateTime.now(), LocalDateTime.now().plusHours(5),
//					1000, Location.JAKARTA);
//			log.info("Event: " + event);
//		};
//	}

}
