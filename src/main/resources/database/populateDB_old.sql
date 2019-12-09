--USE toleksiv_store;
-- 01. Populate manufacturers;
INSERT INTO manufacturers (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Asics'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Saucony'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Nike'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Craft');

-- 02. Populate products (product could be produced only by 1 manufacturer);
--SELECT @id:=id FROM manufacturers WHERE name = 'Asics';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Asics FUZEX SEAMLESS SS GRY M',id, 832 FROM manufacturers WHERE name = 'Asics';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Asics FUZEX PADDED VEST BLK M', id, 1664 FROM manufacturers WHERE name = 'Asics';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Asics LITE-SHOW VEST GRY/BLK M', id, 2392 FROM manufacturers WHERE name = 'Asics';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Asics FUZEX BOMBER JACKET BLK M', id, 1875 FROM manufacturers WHERE name = 'Asics';

--SELECT @id:=id FROM manufacturers WHERE name = 'Saucony';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Saucony VELOCITY LONG SLEEVE', id, 840 FROM manufacturers WHERE name = 'Saucony';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Saucony VITARUN JACKET', id, 2340 FROM manufacturers WHERE name = 'Saucony';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Saucony SONIC REFLEX JACKET', id, 1782 FROM manufacturers WHERE name = 'Saucony';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Saucony SIBERIUS JACKET', id, 2190 FROM manufacturers WHERE name = 'Saucony';

--SELECT @id:=id FROM manufacturers WHERE name = 'Nike';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Nike DF AEROREACT SS', id, 2369 FROM manufacturers WHERE name = 'Nike';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Nike 7 PHENOM 2-IN-1 SHORT', id, 1229 FROM manufacturers WHERE name = 'Nike';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Nike 5 PHENOM 2-IN-1 SHORT', id, 1169 FROM manufacturers WHERE name = 'Nike';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Nike DRI-FIT STRETCH WOVEN PANT', id, 1869 FROM manufacturers WHERE name = 'Nike';

--SELECT @id:=id FROM manufacturers WHERE name = 'Craft';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Craft MIND SHORTSLEEVE TEE', id, 800 FROM manufacturers WHERE name = 'Craft';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Craft Essential short tights M', id, 1088 FROM manufacturers WHERE name = 'Craft';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Craft Classic Thermal Jersey M', id, 1945 FROM manufacturers WHERE name = 'Craft';
INSERT INTO products (id, name, manufacturer_id, price)
  SELECT uuid_in(md5(random()::text || now()::text)::cstring), 'Craft EBC Aero Tri Top M', id, 2240 FROM manufacturers WHERE name = 'Craft';

-- 03. Populate roles;
INSERT INTO roles (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_ADMIN'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_USER');

-- 04. Populate users;
INSERT INTO users (id, username, firstname, lastname, password, email) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'admin', 'admin', 'admin', '$2a$10$MBNLCmt0VanScq0U.glhwulABB06a5z8RIBFOsL4xhoxegaSdNuH6','admin@example.com'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'toleksiv', 'taras', 'oleksiv', '$2a$10$d1dnh071WHfnTzSaHL.T3.loKhECNnmEqwgPMYhTgpwuEbvMSngpu','toleksiv@example.com'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'guest', 'guest', 'guest', '$2a$10$ODDivyvkir2Oh1RHc5ajb.5Ftv7Q2wUUoqhyojSyxvny8rmPYt03a','guest@example.com');

-- 05. Populate user roles;
--SELECT @roleAdminid:=id FROM roles WHERE name = 'ROLE_ADMIN';
--SELECT @roleUserid:=id FROM roles WHERE name = 'ROLE_USER';
--SELECT @userid:=id FROM users WHERE username = 'admin';

INSERT INTO user_roles (user_id, role_id)
  SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'admin'  AND r.name = 'ROLE_ADMIN';
--SELECT @userid:=id FROM users WHERE username = 'guest';
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'guest'  AND r.name = 'ROLE_USER';
--SELECT @userid:=id FROM users WHERE username = 'toleksiv';
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'toleksiv'  AND r.name = 'ROLE_ADMIN';
INSERT INTO user_roles (user_id, role_id)
  SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'toleksiv'  AND r.name = 'ROLE_USER';
