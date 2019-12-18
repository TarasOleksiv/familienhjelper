-- Create new DB on server;
--DROP SCHEMA IF EXISTS toleksiv_store;
--CREATE SCHEMA toleksiv_store DEFAULT CHARACTER SET utf8 ;
--USE toleksiv_store;
-- 01. Create table manufacturers;
--CREATE TABLE manufacturers (
--  id UUID NOT NULL,
--  name VARCHAR(45) NOT NULL,
--  PRIMARY KEY (id),
--  UNIQUE (name))
--  ;
  -- 02. Create table products (product could be produced only by 1 manufacturer);
--CREATE TABLE products (
--  id UUID NOT NULL,
--  name varchar(45) NOT NULL,
--  manufacturer_id UUID NOT NULL,
--  price DECIMAL(7,2) NOT NULL,
--  PRIMARY KEY (id),
--  UNIQUE (name,manufac

-- 01. Create table roles;turer_id),
CREATE TABLE roles (
  id UUID NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name))
  ;
-- 02. Create table users;
CREATE TABLE users (
  id UUID NOT NULL,
  username VARCHAR(48) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(96) NULL,
  mobile1 VARCHAR(25) NULL,
  mobile2 VARCHAR(25) NULL,
  address VARCHAR(96) NULL,
  account VARCHAR(255) NULL,
  bank VARCHAR(96) NULL,
  PRIMARY KEY (id),
  UNIQUE (username))
  ;
-- 03. Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id UUID NOT NULL,
  role_id UUID NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  UNIQUE (user_id, role_id))
  ;

