-- 05. Populate income_types;
INSERT INTO income_types (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Random')
;
