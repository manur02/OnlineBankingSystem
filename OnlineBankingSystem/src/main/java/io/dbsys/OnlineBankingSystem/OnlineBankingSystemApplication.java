package io.dbsys.OnlineBankingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OnlineBankingSystemApplication {

	@RequestMapping("/")
	public String welcome(){
		return "<h1>Yayy!! application is live</h1>";
	}
	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingSystemApplication.class, args);
	}

}
