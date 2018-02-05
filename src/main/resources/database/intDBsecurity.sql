USE toleksiv_store;
# 03. Create table roles;
CREATE TABLE roles (
  id BINARY(16) NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX ROLE_IDX (name ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
# 04. Create table users;
CREATE TABLE users (
  id BINARY(16) NOT NULL,
  username VARCHAR(45) NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX USERNAME_IDX (username ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
# 05. Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id BINARY(16) NOT NULL,
  role_id BINARY(16) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;