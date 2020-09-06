package codes.purple.chabarok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class ChabarokApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(ChabarokApplication.class, args);
	}

}
