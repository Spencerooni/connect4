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
