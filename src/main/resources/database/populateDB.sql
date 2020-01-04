
-- 01. Populate roles;
INSERT INTO roles (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_ADMIN'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_FIELDCONTACT'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_FU')
;

-- 02. Populate users;
INSERT INTO users (id, username, password, email) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'admin', '$2a$10$MBNLCmt0VanScq0U.glhwulABB06a5z8RIBFOsL4xhoxegaSdNuH6','admin@example.com'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'toleksiv', '$2a$10$d1dnh071WHfnTzSaHL.T3.loKhECNnmEqwgPMYhTgpwuEbvMSngpu','toleksiv@example.com'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'guest', '$2a$10$ODDivyvkir2Oh1RHc5ajb.5Ftv7Q2wUUoqhyojSyxvny8rmPYt03a','guest@example.com')
;

-- 03. Populate user roles;
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'admin'  AND r.name = 'ROLE_ADMIN';
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'guest'  AND r.name = 'ROLE_FU';
INSERT INTO user_roles (user_id, role_id)
 SELECT u.id AS user_id, r.id AS role_id FROM users u, roles r WHERE u.username = 'toleksiv'  AND r.name = 'ROLE_FIELDCONTACT'
;

-- 04. Populate statuses;
INSERT INTO statuses (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'idea'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'one time'),
  (uuid_in(md5(random()::text || now()::text)::cstring), '3 months'),
  (uuid_in(md5(random()::text || now()::text)::cstring), '6 months'),
  (uuid_in(md5(random()::text || now()::text)::cstring), '12 months'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'indefinite'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'closed')
;

-- 05. Populate income_types;
INSERT INTO income_types (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Salary'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Pension'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'None')
;

-- 06. Populate currency;
INSERT INTO currency (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'UAH'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'RUB'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'EUR'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'USD')
;

-- 07. Populate donor_types;
INSERT INTO donor_types (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Unknown'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Donor'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Regular donor'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Member')
;

-- 08. Populate transaction_types;
INSERT INTO transaction_types (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Donation'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Regular donation'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Fund raiser'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Norsk Tipping'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Transfer fee'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Field expense'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Expense'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Individual'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Family'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Activity'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Institution')
;

-- 09. Populate currency_rates;
INSERT INTO currency_rates (id, sourceCurrency, targetCurrency, rate) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'EUR', 9.82),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'NOK', 1),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'RUB', 0.14),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'UAH', 0.37),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'USD', 8.80)
;
