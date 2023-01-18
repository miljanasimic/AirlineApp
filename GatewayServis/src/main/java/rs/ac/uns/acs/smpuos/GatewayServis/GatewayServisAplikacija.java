package rs.ac.uns.acs.smpuos.GatewayServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServisAplikacija /*implements WebMvcConfigurer*/{

	public static void main(String[] args) {
		SpringApplication.run(GatewayServisAplikacija.class, args);
	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//				.allowedHeaders("*")
//				.allowedOrigins("*");
//		WebMvcConfigurer.super.addCorsMappings(registry);
//	}
}
