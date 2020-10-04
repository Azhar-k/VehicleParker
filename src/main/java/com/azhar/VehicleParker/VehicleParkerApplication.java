package com.azhar.VehicleParker;


import com.azhar.VehicleParker.Dao.LevelRepository;
import com.azhar.VehicleParker.Dao.VehicleRepository;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VehicleParkerApplication implements CommandLineRunner {

	@Autowired
	LevelRepository levelRepository;

	@Autowired
	VehicleRepository vehicleRepository;

	static ApplicationContext applicationContext;

	public static void main(String[] args) {

		applicationContext =SpringApplication.run(VehicleParkerApplication.class, args);
		Database database = applicationContext.getBean(Database.class);
		database.loadData();
	}

	@Override
	public void run(String... args) throws Exception {


		System.out.println(levelRepository.findAll());
	}
}
