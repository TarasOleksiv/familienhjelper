USE toleksiv_store;
# 03. Populate roles;
INSERT INTO roles (id, name) VALUES
  (unhex(replace(uuid(), '-', '')), 'ROLE_ADMIN'),
  (unhex(replace(uuid(), '-', '')), 'ROLE_USER');
# 04. Populate users;
INSERT INTO users (id, username, firstname, lastname, password, email) VALUES
  (unhex(replace(uuid(), '-', '')), 'admin', 'admin', 'admin', '$2a$10$MBNLCmt0VanScq0U.glhwulABB06a5z8RIBFOsL4xhoxegaSdNuH6','admin@example.com'),
  (unhex(replace(uuid(), '-', '')), 'toleksiv', 'taras', 'oleksiv', '$2a$10$d1dnh071WHfnTzSaHL.T3.loKhECNnmEqwgPMYhTgpwuEbvMSngpu','toleksiv@example.com'),
  (unhex(replace(uuid(), '-', '')), 'guest', 'guest', 'guest', '$2a$10$ODDivyvkir2Oh1RHc5ajb.5Ftv7Q2wUUoqhyojSyxvny8rmPYt03a','guest@example.com');
# 05. Populate user roles;
SELECT @roleAdminid:=id FROM roles WHERE name = 'ROLE_ADMIN';
SELECT @roleUserid:=id FROM roles WHERE name = 'ROLE_USER';
SELECT @userid:=id FROM users WHERE username = 'admin';
INSERT INTO user_roles (user_id, role_id) VALUES
  (@userid, @roleAdminid);
SELECT @userid:=id FROM users WHERE username = 'guest';
INSERT INTO user_roles (user_id, role_id) VALUES
  (@userid, @roleUserid);
SELECT @userid:=id FROM users WHERE username = 'toleksiv';
INSERT INTO user_roles (user_id, role_id) VALUES
  (@userid, @roleUserid),
  (@userid, @roleAdminid);
