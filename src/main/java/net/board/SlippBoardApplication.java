package net.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SlippBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlippBoardApplication.class, args);		//
	}
}
