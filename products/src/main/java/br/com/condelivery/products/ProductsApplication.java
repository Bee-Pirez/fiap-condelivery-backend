package br.com.condelivery.products;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		String myEnvVar2 = dotenv.get("SPRING_DATASOURCE_USERNAME");
		String myEnvVar3 = dotenv.get("SPRING_DATASOURCE_PASSWORD");



		SpringApplication.run(ProductsApplication.class, args);
	}

}
