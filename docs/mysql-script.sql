show databases;
create database springsecuritydb;


CREATE TABLE IF NOT EXISTS `springsecuritydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `active` TINYINT NULL,
  `password` VARCHAR(45) NULL,
  `roles` VARCHAR(45) NULL,
  `user_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

insert into (id,active,password,roles,user_name)values(1,1,'pass',"ROLE_USER",'user');