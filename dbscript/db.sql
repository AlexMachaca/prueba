create database dbtestproject;
use dbtestproject;

create table tuser(
idUser char(36) not null,
name varchar(70) not null,
email varchar(100) unique not null,
password varchar(100) not null,
dni char(8) not null,
gender boolean not null, /*true => masculino, false => femenino*/
createdAt datetime not null,
updatedAt datetime not null,
primary key(idUser)
) engine=innodb;

DROP TABLE tuser;

create table tuser(
idUser char(36) not null,
firstName varchar(70) not null,
surName varchar(40) not null,
dni char(8) unique not null,
email varchar(100) unique not null,
primary key(idUser)
) engine=innodb;

select * from tuser

INSERT INTO tuser (
    idUser,
    firstName,
    surName,
    dni,
    email
) VALUES (
    '123e4567-e89b-12d3-a456-426614174000', -- Ejemplo de UUID
    'Ada',
    'Lovelace',
    '12345678',
    'ada.lovelace@example.net'
);