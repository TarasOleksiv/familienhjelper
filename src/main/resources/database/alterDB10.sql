-- 10. Add columns to the table projects
ALTER TABLE projects
    ADD COLUMN beneficiary_id UUID;

ALTER TABLE projects
  ADD CONSTRAINT FK_BENEFICIARY_ID FOREIGN KEY (beneficiary_id) REFERENCES beneficiaries (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

