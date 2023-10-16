-- Active: 1697465824993@@srv-bdens.insa-toulouse.fr@3306@projet_gei_011
CREATE TABLE IF NOT EXISTS USER(
    IdUser INT PRIMARY KEY AUTO_INCREMENT,
    UserName VARCHAR(45) NOT NULL,
    BirthDate DATETIME NOT NULL,
    Email VARCHAR(45) NOT NULL UNIQUE,
    UserType ENUM('Helper','Validator','Needer')
);