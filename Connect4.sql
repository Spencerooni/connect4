CREATE DATABASE Connect4;
USE Connect4;
SET AUTO_COMMIT = 0;

DROP TABLE IF EXISTS Logins;

CREATE TABLE Logins
(
username varchar(50) NOT NULL PRIMARY KEY,
password varchar(50) NOT NULL,
accessNo int NOT NULL
);

DROP TABLE IF EXISTS Employees; 

CREATE TABLE Employees (
	forename varchar(30) NOT NULL,
	surname varchar(30) NOT NULL,
	addressLine1 varchar(30),
	addressLine2 varchar(30),	
	town varchar(30),
	county varchar(30),
	postcode varchar(8),
	nino varchar(9) UNIQUE,
	bankNo varchar(34) UNIQUE,
	startingSalary double,
	employeeNo int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	departmentId int
)

drop table if exists Departments;

create table Departments (
id int(255) primary key auto_increment,
name varchar(50) not null
);

insert into Departments (name)
values ('Evolve');

insert into Departments (name)
values ('Enterprise');

insert into Departments (name)
values ('Gov');
