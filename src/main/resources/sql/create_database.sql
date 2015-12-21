CREATE USER 'racing_simulator'@'localhost' IDENTIFIED BY 'racing_simulator';
CREATE DATABASE IF NOT EXISTS `racing_simulator` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `racing_simulator`.* TO 'racing_simulator'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE `racing_simulator`.`racing` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `distance` DOUBLE NOT NULL,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  PRIMARY KEY (`id`));

CREATE TABLE `racing_simulator`.`vehicle` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `vehicle_type` INT UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `weight` DOUBLE NOT NULL,
  `max_speed` DOUBLE NOT NULL,
  `acceleration` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));
  
CREATE TABLE `racing_simulator`.`bus` (
  `id` BIGINT(20) NOT NULL,
  `num_passengers` INT NOT NULL,
  `max_num_passengers` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `bus_id`
    FOREIGN KEY (`id`)
    REFERENCES `racing_simulator`.`vehicle` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);  

CREATE TABLE `racing_simulator`.`truck` (
  `id` BIGINT(20) NOT NULL,
  `payload_weight` DOUBLE NOT NULL,
  `max_payload_weight` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `truck_id`
    FOREIGN KEY (`id`)
    REFERENCES `racing_simulator`.`vehicle` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE `racing_simulator`.`car_trailer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `weight` DOUBLE NOT NULL,
  `max_speed` DOUBLE NOT NULL,
  `payload_weight` DOUBLE NOT NULL,
  `max_payload_weight` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `racing_simulator`.`car` (
  `id` BIGINT(20) NOT NULL,
  `trailer_id` BIGINT(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `car_trailer_id_idx` (`trailer_id` ASC),
  CONSTRAINT `car_id`
    FOREIGN KEY (`id`)
    REFERENCES `racing_simulator`.`vehicle` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `car_trailer_id`
    FOREIGN KEY (`trailer_id`)
    REFERENCES `racing_simulator`.`car_trailer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `racing_simulator`.`racer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `race_date` TIMESTAMP(6) NOT NULL,
  `racing_id` BIGINT(20) NOT NULL,
  `vehicle_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `racer_racing_id_idx` (`racing_id` ASC),
  INDEX `racer_vehicle_id_idx` (`vehicle_id` ASC),
  CONSTRAINT `racer_racing_id`
    FOREIGN KEY (`racing_id`)
    REFERENCES `racing_simulator`.`racing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `racer_vehicle_id`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `racing_simulator`.`vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
