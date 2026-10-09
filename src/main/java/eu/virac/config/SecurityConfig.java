package eu.virac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	public PasswordEncoder getEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
		}
		@Bean
		public UserDetailsManager getUserDetailsManager() {
			MyUserDetailsManager manager = new MyUserDetailsManager();
			return manager;
		}
		@Bean
		public DaoAuthenticationProvider setProvider() {
			DaoAuthenticationProvider dao = new DaoAuthenticationProvider(getUserDetailsManager());
			dao.setPasswordEncoder(getEncoder());
			return dao;
		}
	@Bean
		public SecurityFilterChain httpPremisions(HttpSecurity http) {
			http.authorizeHttpRequests(auth->auth
					.requestMatchers("/workdescriptions/crud/**").hasAuthority("ADMIN")
					.requestMatchers("/workdescriptions/filter/**").hasAuthority("USER"));
			http.formLogin(auth->auth.permitAll());
			return http.build();
		}
}
