package edu.poly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.poly.model.User;

@SpringBootApplication
public class QlNhanVienApplication {

	public static void main(String[] args) {
		SpringApplication.run(QlNhanVienApplication.class, args);
	}
	
	@Bean(name = "USER_BEAN")
	public User setUser() {
		User u = new User();
		u.setUsername("admin");
		u.setPassword("123");
		return u;
	}

}
