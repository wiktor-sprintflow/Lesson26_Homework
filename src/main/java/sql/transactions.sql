CREATE SCHEMA lesson26_homework;

CREATE TABLE transaction (
	id INT auto_increment PRIMARY KEY,
	type VARCHAR(50) NOT NULL,
    description VARCHAR(100),
	amount DECIMAL(10,2) NOT NULL,
    date DATE NOT NULL
);