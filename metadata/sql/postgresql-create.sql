-- drop the existing database
drop database canyon_db;

-- create the test user
create user test password 'test';

-- create the database
create database canyon_db owner test;
