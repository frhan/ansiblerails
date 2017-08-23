CREATE TABLE roles(
id bigint not null auto_increment,
name VARCHAR (255) NOT NULL UNIQUE,
primary key (id)
);

ALTER TABLE users ADD role_id bigint;
ALTER TABLE users ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles(id);
