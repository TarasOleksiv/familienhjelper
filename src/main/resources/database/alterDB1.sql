-- 01. Add 2 columns to the table users
ALTER TABLE users
    ADD COLUMN firstname VARCHAR(48);

ALTER TABLE users
    ADD COLUMN lastname VARCHAR(48);