-- 07. Add HELPER role to roles;
INSERT INTO roles (id, name) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'ROLE_HELPER')
;
