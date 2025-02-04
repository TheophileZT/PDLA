-- Active: 1697465824993@@srv-bdens.insa-toulouse.fr@3306@projet_gei_011
CREATE TABLE IF NOT EXISTS USER(
    IdUser INT PRIMARY KEY AUTO_INCREMENT,
    UserFirstName VARCHAR(20) NOT NULL,
    UserLastName VARCHAR(20) NOT NULL,
    BirthDate DATETIME,
    Email VARCHAR(45) NOT NULL UNIQUE,
    UserType ENUM('Helper','Validator','Needer'),
    Password VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS MISSIONS(
    IdMission INT PRIMARY KEY AUTO_INCREMENT,
    IdNeeder INT NOT NULL,
    IdHelper INT,
    Title VARCHAR(45) NOT NULL,
    Description VARCHAR(500) NOT NULL,
    Date DATETIME NOT NULL,
    Status ENUM('Pending','Accepted','Refused','Done') NOT NULL,
    FOREIGN KEY (IdNeeder) REFERENCES USER(IdUser),
    FOREIGN KEY (IdHelper) REFERENCES USER(IdUser)
);