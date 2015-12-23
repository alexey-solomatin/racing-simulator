CREATE USER 'racing_simulator'@'localhost' IDENTIFIED BY 'racing_simulator';
CREATE DATABASE IF NOT EXISTS `racing_simulator` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `racing_simulator`.* TO 'racing_simulator'@'localhost';
GRANT SELECT ON mysql.proc TO 'racing_simulator'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE `racing_simulator`.`racing` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
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
  `race_date` TIMESTAMP NOT NULL,
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

USE `racing_simulator`;
DROP procedure IF EXISTS `create_racing`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `create_racing` (IN sName VARCHAR(255), IN dDistance DOUBLE, OUT id BIGINT(20))
BEGIN
    INSERT INTO `racing` (`name`, `distance`) VALUES (sName, dDistance);
    SET id = last_insert_id();
END$$

DELIMITER ;    
    
USE `racing_simulator`;
DROP procedure IF EXISTS `create_bus`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `create_bus` (IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dAcceleration DOUBLE, IN lNumPassengers INT(11), IN lMaxNumPassengers INT(11), OUT id BIGINT(20))
BEGIN
	START TRANSACTION;
    INSERT INTO `vehicle` (`vehicle_type`, `name`, `weight`, `max_speed`, `acceleration`) VALUES (1, sName, dWeight, dMaxSpeed, dAcceleration);
    SET id = last_insert_id();
	INSERT INTO `bus` (`id`, `num_passengers`, `max_num_passengers`) VALUES (id, lNumPassengers, lMaxNumPassengers);
    COMMIT;
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `create_truck`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `create_truck` (IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dAcceleration DOUBLE, IN dPayloadWeight DOUBLE, IN dMaxPayloadWeight DOUBLE, OUT id BIGINT(20))
BEGIN
	START TRANSACTION;
    INSERT INTO `vehicle` (`vehicle_type`, `name`, `weight`, `max_speed`, `acceleration`) VALUES (2, sName, dWeight, dMaxSpeed, dAcceleration);
    SET id = last_insert_id();
	INSERT INTO `truck` (`id`, `payload_weight`, `max_payload_weight`) VALUES (last_insert_id(), dPayloadWeight, dMaxPayloadWeight);
    COMMIT;
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `create_car`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `create_car` (IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dAcceleration DOUBLE, IN lTrailerId BIGINT(20), OUT id BIGINT(20))
BEGIN
	START TRANSACTION;
    INSERT INTO `vehicle` (`vehicle_type`, `name`, `weight`, `max_speed`, `acceleration`) VALUES (3, sName, dWeight, dMaxSpeed, dAcceleration);
    SET id = last_insert_id();
	INSERT INTO `car` (`id`, `trailer_id`) VALUES (last_insert_id(), lTrailerId);
    COMMIT;
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `create_car_trailer`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `create_car_trailer` (IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dPayloadWeight DOUBLE, IN dMaxPayloadWeight DOUBLE, OUT id BIGINT(20))
BEGIN
    INSERT INTO `car_trailer` (`name`, `weight`, `max_speed`, `payload_weight`, `max_payload_weight`) VALUES (sName, dWeight, dMaxSpeed, dPayloadWeight, dMaxPayloadWeight);
    SET id = last_insert_id();
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `update_racing`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `update_racing` (IN id BIGINT(20), IN sName VARCHAR(255), IN dDistance DOUBLE)
BEGIN
    UPDATE `racing` r
    SET r.`name` = sName, r.`distance` = dDistance
    WHERE r.id = id;
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `update_bus`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `update_bus` (IN id BIGINT(20), IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dAcceleration DOUBLE, IN lNumPassengers INT(11), IN lMaxNumPassengers INT(11))
BEGIN
	START TRANSACTION;
    UPDATE `vehicle` v
    SET v.`name` = sName, v.`weight` = dWeight, v.`max_speed` = dMaxSpeed, v.`acceleration` = dAcceleration
    WHERE v.id = id;
	UPDATE `bus` b 
	SET b.`num_passengers` = lNumPassengers, b.`max_num_passengers` = lMaxNumPassengers
	WHERE b.id = id;
    COMMIT;
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `update_truck`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `update_truck` (IN id BIGINT(20), IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dAcceleration DOUBLE, IN dPayloadWeight DOUBLE, IN dMaxPayloadWeight DOUBLE)
BEGIN
	START TRANSACTION;
    UPDATE `vehicle` v 
    SET v.`name` = sName, v.`weight` = dWeight, v.`max_speed` = dMaxSpeed, v.`acceleration` = dAcceleration
    WHERE v.id = id;
	UPDATE `truck` t 
	SET t.`payload_weight` = dPayloadWeight, t.`max_payload_weight` = dMaxPayloadWeight
    WHERE t.id = id;
    COMMIT;
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `update_car`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `update_car` (IN id BIGINT(20), IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dAcceleration DOUBLE, IN lTrailerId BIGINT(20))
BEGIN
	START TRANSACTION;
    UPDATE `vehicle` v 
    SET v.`name` = sName, v.`weight` = dWeight, v.`max_speed` = dMaxSpeed, v.`acceleration` = dAcceleration
    WHERE v.id = id;
	UPDATE `car` c 
	SET c.`trailer_id` = lTrailerId
	WHERE c.id = id;
    COMMIT;
END$$

DELIMITER ;

USE `racing_simulator`;
DROP procedure IF EXISTS `update_car_trailer`;

DELIMITER $$
USE `racing_simulator`$$
CREATE PROCEDURE `update_car_trailer` (IN id BIGINT(20), IN sName VARCHAR(255), IN dWeight DOUBLE, IN dMaxSpeed DOUBLE, IN dPayloadWeight DOUBLE, IN dMaxPayloadWeight DOUBLE)
BEGIN
    UPDATE `car_trailer` ct
    SET ct.`name` = sName, ct.`weight` = dWeight, ct.`max_speed` = dMaxSpeed, ct.`payload_weight` = dPayloadWeight, ct.`max_payload_weight` = dMaxPayloadWeight
    WHERE ct.id = id;
END$$

DELIMITER ;