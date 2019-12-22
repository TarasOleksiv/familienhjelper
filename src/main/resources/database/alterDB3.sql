-- 04. Add 3 columns to the table beneficiaries
ALTER TABLE beneficiaries
    ADD COLUMN user_id UUID;

ALTER TABLE beneficiaries
  ADD COLUMN incometype_id UUID;

ALTER TABLE beneficiaries
  ADD COLUMN currency_id UUID;

ALTER TABLE beneficiaries
  ADD CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE beneficiaries
  ADD CONSTRAINT FK_INCOMETYPE_ID FOREIGN KEY (incometype_id) REFERENCES income_types (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE beneficiaries
  ADD CONSTRAINT FK_CURRENCY_ID FOREIGN KEY (currency_id) REFERENCES currency (id) ON DELETE NO ACTION ON UPDATE NO ACTION;