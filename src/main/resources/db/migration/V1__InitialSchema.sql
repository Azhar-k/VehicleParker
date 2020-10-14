CREATE TABLE `level` (
  `number` int NOT NULL,
  PRIMARY KEY (`number`)
);

CREATE TABLE `vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parking_rate` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
);

CREATE TABLE `level_parked_vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `level_number` int NOT NULL,
  `parking_rate` int NOT NULL,
  `time` time DEFAULT NULL,
  `vehicle_name` varchar(255) DEFAULT NULL,
  `vehicle_number` varchar(255) DEFAULT NULL,
  `vehicle_type` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`vehicle_number`)
);

CREATE TABLE `allowed_vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `max_slots` int NOT NULL,
  `free_slots` int NOT NULL,
  `occupied_slots` int NOT NULL,
  `level_number` int DEFAULT NULL,
  `vehicle_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`level_number`) REFERENCES `level` (`number`),
  FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ;
