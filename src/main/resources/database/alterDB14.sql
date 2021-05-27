-- 14. Add column active to the table users
ALTER TABLE users
  ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE
;
