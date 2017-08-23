CREATE TABLE users(
id bigint not null auto_increment,
email VARCHAR (255) NOT NULL UNIQUE,
password VARCHAR (100) NOT NULL,
primary key (id)
);