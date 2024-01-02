package com.isg.ws;

import com.isg.ws.user.User;
import com.isg.ws.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}
	@Bean
	@Profile("dev")
	CommandLineRunner userCreator(UserRepository userRepository){
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				for(var i=1;i<=25;i++){
					User user=new User();
					user.setUsername("user"+i);
					user.setEmail("user"+i+"@mail.com");
					user.setPassword(passwordEncoder.encode("P4assword"));
					user.setActive(true);
					userRepository.save(user);
				}
			}
		};
	}
}
