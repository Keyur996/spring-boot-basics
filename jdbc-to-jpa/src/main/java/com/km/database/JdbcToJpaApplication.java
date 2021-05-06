package com.km.database;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.km.database.entity.Person;
import com.km.database.repo.PersonRepo;

@SpringBootApplication
public class JdbcToJpaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonRepo personRepo;

	public static void main(String[] args) {
		SpringApplication.run(JdbcToJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		logger.info("Persons -> {}", dao.findAll());
//		logger.info("User -> {}", dao.findById(1001));
//		logger.info("Deleted Person/Persons count -> {}", dao.deleteById(1002));
//		logger.info("Upadte count -> {}", dao.update(new Person(1001, "Keyur Machhi", "Dahod", new Date())));
//		logger.info("Insert count -> {}", dao.insert(new Person(1004, "Viraj Prajapati", "Gandhinagar", new Date())));
//		logger.info("Persons -> {}", dao.findAll());

		logger.info("User -> {}", personRepo.findById(1001));
		logger.info("Insert count -> {}",
				personRepo.insert(new Person("Viraj Prajapati", "Dahod", new Date())));
		logger.info("Update count -> {}",
				personRepo.update(new Person(1002, "Viraj Prajapati", "Gandhinagar", new Date())));
		personRepo.deleteById(1001);
		logger.info("Persons -> {}", personRepo.findAll());
	}

}
