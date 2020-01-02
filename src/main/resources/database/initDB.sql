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
  firstname VARCHAR(48),
  lastname VARCHAR(48),
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

-- 04. Create table statuses
CREATE TABLE statuses (
  id UUID NOT NULL,
  name VARCHAR(48),
  PRIMARY KEY (id)
)
;

-- 05. Create table income_types
CREATE TABLE income_types (
  id UUID NOT NULL,
  name VARCHAR(48),
  PRIMARY KEY (id)
)
;

-- 06. Create table currency
CREATE TABLE currency (
  id UUID NOT NULL,
  name VARCHAR(3),
  PRIMARY KEY (id)
)
;

-- 07. Create table donor_types
CREATE TABLE donor_types (
  id UUID NOT NULL,
  name VARCHAR(48),
  PRIMARY KEY (id)
)
;

-- 08. Create table members;
CREATE TABLE members (
  id UUID NOT NULL,
  name VARCHAR(48) NOT NULL,
  email VARCHAR(96) NULL,
  mobile VARCHAR(25) NULL,
  city VARCHAR(96) NULL,
  account VARCHAR(255) NULL,
  bank VARCHAR(96) NULL,
  donortype_id UUID,
  PRIMARY KEY (id),
  CONSTRAINT FK_DONORTYPE_ID FOREIGN KEY (donortype_id) REFERENCES donor_types (id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
;

-- 09. Create table beneficiaries
CREATE TABLE beneficiaries (
  id UUID NOT NULL,
  name VARCHAR(48) NOT NULL,
  family VARCHAR(480) NULL,
  income DECIMAL(11,2) NULL,
  description VARCHAR(2000) NULL,
  datefield DATE,
  user_id UUID,
  incometype_id UUID,
  currency_id UUID,
  PRIMARY KEY (id),
  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_INCOMETYPE_ID FOREIGN KEY (incometype_id) REFERENCES income_types (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_CURRENCY_ID FOREIGN KEY (currency_id) REFERENCES currency (id) ON DELETE NO ACTION ON UPDATE NO ACTION
  )
;

-- 10. Create table projects
CREATE TABLE projects (
  id UUID NOT NULL,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(2000) NULL,
  startDate DATE NULL,
  stopDate DATE NULL,
  feedback VARCHAR(2000) NULL,
  balance DECIMAL(11,2) DEFAULT 0.00 NULL,
  fieldContact_id UUID,
  fu_id UUID,
  status_id UUID,
  PRIMARY KEY (id),
  UNIQUE (name),
  CONSTRAINT FK_FIELDCONTACT_ID FOREIGN KEY (fieldContact_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_FU_ID FOREIGN KEY (fu_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id) REFERENCES statuses (id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
;

-- 11. Create table project_member
CREATE TABLE project_member (
  id UUID NOT NULL,
  project_id UUID NOT NULL,
  member_id UUID NOT NULL,
  pledge DECIMAL(11,2) NULL,
  startPledge DATE NULL,
  stopPledge DATE NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_PROJECT_ID FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_MEMBER_ID FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
;

-- 12. Create table images
CREATE TABLE images (
  id UUID NOT NULL,
  project_id UUID NOT NULL,
  description VARCHAR(2000) NULL,
  link VARCHAR(1000) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_PROJECT_ID FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
;


