DROP DATABASE IF EXISTS Connect4;
CREATE DATABASE Connect4;
USE Connect4;

drop table if exists Departments;

create table Departments (
id int(255) primary key auto_increment,
name varchar(50) not null
);

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
);

insert into Departments (name)
values ('Evolve');

insert into Departments (name)
values ('Enterprise'); 

insert into Departments (name)
values ('Gov');

insert into Logins (username, password, accessNo) values ("hr@kainos.com", "password1", 1);
insert into Logins (username, password, accessNo) values ("finance@kainos.com", "password2", 2); 

INSERT INTO Employees (forename, surname, addressLine1, addressLine2, town, county, postcode, nino, bankNo, startingSalary, departmentId) 
Values ("Aoife", "Gildernew", "54 New Street", "New Road", "New Town", "New County", "BT67 3DK", "PB638474D", 10000, 1);

INSERT INTO Employees (forename, surname, addressLine1, addressLine2, town, county, postcode, nino, bankNo, startingSalary, departmentId) 
Values ("Ben", "Leonard", "7 Street", "Road", "Town", "County", "BT67 6HF", "PP784389A", 12500.50, 2);

alter table Employees add constraint fk_Employees_ref_Departments foreign key Employees(departmentId) references Departments(id);
