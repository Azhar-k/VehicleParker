package com.azhar.VehicleParker;

import com.azhar.VehicleParker.Services.SpaceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VehicleParkerApplication implements CommandLineRunner {
	@Autowired
	static Database database;
	public static void main(String[] args) {

		ApplicationContext applicationContext =SpringApplication.run(VehicleParkerApplication.class, args);

		applicationContext.getBean(Database.class).loadData();
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
