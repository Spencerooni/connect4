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
