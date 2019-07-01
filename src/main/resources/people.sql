CREATE DATABASE IF NOT EXISTS `jdbc_example`;

use jdbc_example;

drop table if exists people;

CREATE TABLE if not exists people(
id int NOT NULL AUTO_INCREMENT,
job VARCHAR(255),
age INT,
PRIMARY KEY (id));

INSERT INTO people(job,age) values ('pilot',43);
INSERT INTO people(job,age) values ('doctor',23);
INSERT INTO people(job,age) values ('accountant',65);

select * from people;