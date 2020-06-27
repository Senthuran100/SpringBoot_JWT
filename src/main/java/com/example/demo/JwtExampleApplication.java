package com.example.demo;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JwtExampleApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(JwtExampleApplication.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Value("${admin.username}")
	private String username;

	@Value("${admin.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(JwtExampleApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		if(userRepository.findByUsername(username)==null){
			Set<Role> roles= new HashSet<>();
			Role userrole= roleRepository.findByName("USER");
			Role adminrole= roleRepository.findByName("ADMIN");
			roles.add(userrole);
			roles.add(adminrole);

			User user = new User();
			user.setUsername(username);
			user.setPassword(bcryptEncoder.encode(password));
			user.setRoles(roles);
			userRepository.save(user);
			logger.info(username +" user is added. " + username + " has Admin Role.");
		} else {
			logger.info(username + " user already exists. "+username + " has Admin Role.");
		}
	}
}
