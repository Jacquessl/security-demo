package com.example.demo;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Demo1Application {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initUsers() {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setName("ROLE_ADMIN");
		RoleEntity roleEntity2 = new RoleEntity();
		roleEntity2.setName("ROLE_USER");
		RoleEntity roleEntity3 = new RoleEntity();
		roleEntity3.setName("ROLE_JAKUB");
		List<UserEntity> users = Stream.of(
				new UserEntity(0, "Jakub", "password", new ArrayList<>()),
				new UserEntity(1, "Admin", "admin", new ArrayList<>()),
				new UserEntity(2, "User", "password", new ArrayList<>())
		).collect(Collectors.toList());
		users.get(1).getRoles().add(roleEntity);
		users.get(0).getRoles().add(roleEntity3);
		users.get(2).getRoles().add(roleEntity2);
		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
