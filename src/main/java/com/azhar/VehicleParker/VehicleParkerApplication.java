package com.azhar.VehicleParker;

import com.azhar.VehicleParker.Services.SpaceManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VehicleParkerApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext applicationContext =SpringApplication.run(VehicleParkerApplication.class, args);

		SpaceManager spaceManager = applicationContext.getBean(SpaceManager.class);

		System.out.println(spaceManager.getLAvailableSpace().get(0).getAvailableBikeSpace());
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
