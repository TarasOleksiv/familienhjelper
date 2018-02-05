USE toleksiv_store;
# 01. Populate manufacturers;
INSERT INTO manufacturers (id, name) VALUES
  (unhex(replace(uuid(), '-', '')), 'Asics'),
  (unhex(replace(uuid(), '-', '')), 'Saucony'),
  (unhex(replace(uuid(), '-', '')), 'Nike'),
  (unhex(replace(uuid(), '-', '')), 'Craft');
# 02. Populate products (product could be produced only by 1 manufacturer);
SELECT @id:=id FROM manufacturers WHERE name = 'Asics';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Asics FUZEX SEAMLESS SS GRY M', @id,832),
  (unhex(replace(uuid(), '-', '')), 'Asics FUZEX PADDED VEST BLK M', @id,1664),
  (unhex(replace(uuid(), '-', '')), 'Asics LITE-SHOW VEST GRY/BLK M', @id,2392),
  (unhex(replace(uuid(), '-', '')), 'Asics FUZEX BOMBER JACKET BLK M', @id,1875);
SELECT @id:=id FROM manufacturers WHERE name = 'Saucony';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Saucony VELOCITY LONG SLEEVE', @id,840),
  (unhex(replace(uuid(), '-', '')), 'Saucony VITARUN JACKET', @id,2340),
  (unhex(replace(uuid(), '-', '')), 'Saucony SONIC REFLEX JACKET', @id,1782),
  (unhex(replace(uuid(), '-', '')), 'Saucony SIBERIUS JACKET', @id,2190);
SELECT @id:=id FROM manufacturers WHERE name = 'Nike';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Nike DF AEROREACT SS', @id,2369),
  (unhex(replace(uuid(), '-', '')), 'Nike 7 PHENOM 2-IN-1 SHORT', @id,1229),
  (unhex(replace(uuid(), '-', '')), 'Nike 5 PHENOM 2-IN-1 SHORT', @id,1169),
  (unhex(replace(uuid(), '-', '')), 'Nike DRI-FIT STRETCH WOVEN PANT', @id,1869);
SELECT @id:=id FROM manufacturers WHERE name = 'Craft';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Craft MIND SHORTSLEEVE TEE', @id,800),
  (unhex(replace(uuid(), '-', '')), 'Craft Essential short tights M', @id,1088),
  (unhex(replace(uuid(), '-', '')), 'Craft Classic Thermal Jersey M', @id,1945),
  (unhex(replace(uuid(), '-', '')), 'Craft EBC Aero Tri Top M', @id,2240);
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
