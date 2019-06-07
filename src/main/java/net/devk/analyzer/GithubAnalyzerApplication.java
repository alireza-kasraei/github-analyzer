package net.devk.analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubAnalyzerApplication {

	@Bean
	public RestTemplate restTempplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().set("Accept", "application/vnd.github.v3+json");
			return execution.execute(request, body);
		});
		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(GithubAnalyzerApplication.class, args);
	}

}
