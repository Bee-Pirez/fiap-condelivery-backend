package br.com.condelivery.user;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		String myEnvVar2 = dotenv.get("SPRING_DATASOURCE_USERNAME");
		String myEnvVar3 = dotenv.get("SPRING_DATASOURCE_PASSWORD");



		SpringApplication.run(UserApplication.class, args);
	}

}
