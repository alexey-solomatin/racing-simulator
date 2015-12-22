REVOKE ALL ON `racing_simulator`.* FROM 'racing_simulator'@'localhost';
FLUSH PRIVILEGES;
DROP DATABASE IF EXISTS `racing_simulator`;
DROP USER 'racing_simulator'@'localhost';