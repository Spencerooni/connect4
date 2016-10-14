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

DROP TABLE IF EXISTS Sales;

CREATE TABLE Sales (
        employeeNo int NOT NULL UNIQUE,
        commissionRate double,
        totalSales double
);

insert into Departments (name)
values ('Evolve');

insert into Departments (name)
values ('Enterprise'); 

insert into Departments (name)
values ('Gov');

insert into Logins (username, password, accessNo) values ("hr@kainos.com", "password1", 1);

insert into Logins (username, password, accessNo) values ("finance@kainos.com", "password2", 2); 
insert into Logins (username, password, accessNo) values ("manager@kainos.com", "password2", 3); 
insert into Logins (username, password, accessNo) values ("talentmanager@kainos.com", "password2", 4); 

INSERT INTO Employees (forename, surname, addressLine1, addressLine2, town, county, postcode, nino, bankNo, startingSalary, departmentId) 
Values ("Aoife", "Gildernew", "54 New Street", "New Road", "New Town", "New County", "BT67 3DK", "PB638474D", "BANKNO1", 10000, 1);

INSERT INTO Employees (forename, surname, addressLine1, addressLine2, town, county, postcode, nino, bankNo, startingSalary, departmentId) 
Values ("Ben", "Leonard", "7 Street", "Road", "Town", "County", "BT67 6HF", "PP784389A", "BANKNO2", 12500.50, 2);

INSERT INTO Employees (forename, surname, addressLine1, addressLine2, town, county, postcode, nino, bankNo, startingSalary, departmentId)
VALUES ("Joshua", "Knigt", "98 Address","Address 2", "Town", "County", "BT7 9DB", "LM674387C", "BANKNO3", 25000, 1);

INSERT INTO Employees (forename, surname, addressLine1, addressLine2, town, county, postcode, nino, bankNo, startingSalary, departmentId)
VALUES ("Corey", "Duffy", "98 New Address", "New Address", "Town", "New County", "BT35 7DH", "BD647387A", "BANKNO4", 10000, 2);

INSERT INTO Sales VALUES (1, 25, 10000);
INSERT INTO Sales VALUES (2, 15, 150000);


alter table Sales add constraint fk_Sales_ref_Employees
foreign key Sales(employeeNo) references Employees(employeeNo);

 

create table Projects (
id int primary key auto_increment,
projectName varchar(50) not null,
departmentId int);


 alter table Projects add constraint fk_Projects_ref_Departments
 foreign key Projects(departmentId) references Departments(id);
 
 
drop table if exists Assignments;
CREATE TABLE `Assignments` (
  `employeeNo` INT NOT NULL,
  `projectId` INT NOT NULL,
  `startDate` DATE,
  `endDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`employeeNo`, `projectId`),
  INDEX `projectId` (`projectId` ASC),
  CONSTRAINT `Assignment_ibfk_1`
    FOREIGN KEY (`employeeNo`)
    REFERENCES `Connect4`.`Employees` (`employeeNo`)
    ON DELETE CASCADE,
  CONSTRAINT `Assignment_ibfk_2`
    FOREIGN KEY (`projectId`)
    REFERENCES `Connect4`.`Projects` (`id`)
    ON DELETE CASCADE);
    
    insert into Projects (projectName, departmentId) values ('Bens project', 1);
    insert into Projects (projectName, departmentId) values ('Bens project2', 2);
    insert into Projects (projectName, departmentId) values ('Bens project2', 3);
