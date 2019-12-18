
-- 04. Populate roles;
INSERT INTO statuses (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'idea'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'one time'),
  (uuid_in(md5(random()::text || now()::text)::cstring), '3 months'),
  (uuid_in(md5(random()::text || now()::text)::cstring), '6 months'),
  (uuid_in(md5(random()::text || now()::text)::cstring), '12 months'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'indefinite'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'closed')
  ;

