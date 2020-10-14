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
insert into `vehicle` (name,parking_rate) values ('car',20),('bus',40),('van',20),('bike',10),('truck',70),('container',100);
insert into `level` (number) values (0),(1),(2),(3),(4),(5),(6),(7);
INSERT INTO `allowed_vehicle`
(`max_slots`,
`free_slots`,
`occupied_slots`,
`level_number`,
`vehicle_id`)
VALUES
(5,5,0,0,6),
(5,5,0,0,5),
(10,10,0,1,2),
(10,10,0,2,1),
(10,10,0,2,3),
(10,10,0,2,4),
(10,10,0,3,1),
(10,10,0,3,3),
(10,10,0,3,4),
(10,10,0,4,1),
(10,10,0,4,3),
(10,10,0,4,4),
(10,10,0,5,1),
(10,10,0,5,3),
(10,10,0,5,4),
(10,10,0,6,1),
(10,10,0,6,3),
(10,10,0,6,4),
(10,10,0,7,1),
(10,10,0,7,3),
(10,10,0,7,4);
