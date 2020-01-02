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
