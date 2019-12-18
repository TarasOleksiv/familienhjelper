
-- 01. Populate roles;
INSERT INTO roles (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_ADMIN'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_FIELDCONTACT'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_FU');

-- 02. Populate users;
INSERT INTO users (id, username, password, email) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'admin', '$2a$10$MBNLCmt0VanScq0U.glhwulABB06a5z8RIBFOsL4xhoxegaSdNuH6','admin@example.com'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'toleksiv', '$2a$10$d1dnh071WHfnTzSaHL.T3.loKhECNnmEqwgPMYhTgpwuEbvMSngpu','toleksiv@example.com'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'guest', '$2a$10$ODDivyvkir2Oh1RHc5ajb.5Ftv7Q2wUUoqhyojSyxvny8rmPYt03a','guest@example.com');

-- 03. Populate user roles;
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'admin'  AND r.name = 'ROLE_ADMIN';
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'guest'  AND r.name = 'ROLE_FU';
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'toleksiv'  AND r.name = 'ROLE_FIELDCONTACT';
