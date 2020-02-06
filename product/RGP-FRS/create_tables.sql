CREATE DATABASE rgpreferral;
\c rgpreferral
CREATE TABLE users (username VARCHAR(50) PRIMARY KEY, passhash BYTEA NOT NULL, passsalt BYTEA NOT NULL, isAdmin BOOLEAN NOT NULL, info JSON);
CREATE TABLE hospital (name VARCHAR PRIMARY KEY, info JSON);