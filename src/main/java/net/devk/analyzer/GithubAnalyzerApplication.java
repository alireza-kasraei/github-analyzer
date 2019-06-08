package net.devk.analyzer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import net.devk.analyzer.github.ServiceUnavailableException;

@SpringBootApplication
public class GithubAnalyzerApplication {

	@Bean
	public RestTemplate restTempplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().set("Accept", "application/vnd.github.v3+json");
			return execution.execute(request, body);
		});
		restTemplate.setErrorHandler(new ResponseErrorHandler() {

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return (response.getStatusCode().series() == Series.SERVER_ERROR
						|| response.getStatusCode().series() == Series.CLIENT_ERROR);
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				if (response.getStatusCode() == HttpStatus.FORBIDDEN)
					throw new ServiceUnavailableException("Rate limit Error!");
			}
		});
		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(GithubAnalyzerApplication.class, args);
	}

}
