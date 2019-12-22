-- 05. Add columns to the table members
ALTER TABLE members
    ADD COLUMN donortype_id UUID;

ALTER TABLE members
  ADD CONSTRAINT FK_DONORTYPE_ID FOREIGN KEY (donortype_id) REFERENCES donor_types (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

