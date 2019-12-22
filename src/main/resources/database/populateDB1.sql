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