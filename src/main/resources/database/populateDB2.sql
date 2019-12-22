-- 07. Populate donor_types;
INSERT INTO donor_types (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Unknown'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Donor'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Regular donor'),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Member')
;
