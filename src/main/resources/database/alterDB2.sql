-- 02. Add column to the table beneficiaries
ALTER TABLE beneficiaries
    ADD COLUMN user_id UUID;

ALTER TABLE users
    ADD COLUMN lastname VARCHAR(48);