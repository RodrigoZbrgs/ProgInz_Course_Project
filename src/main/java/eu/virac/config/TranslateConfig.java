package eu.virac.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;


@Configuration
public class TranslateConfig {
	
	@Bean 
	public Translate translate() throws IOException { // info from here https://www.baeldung.com/java-google-translate-api#initializing-the-translate-client
		GoogleCredentials credentials = GoogleCredentials
				.fromStream(new ClassPathResource("serviceaccount.json").getInputStream());
		
		Translate translateService = TranslateOptions.newBuilder()
				.setCredentials(credentials)
				.build()
				.getService();
				
		return translateService;
	}
	
}
