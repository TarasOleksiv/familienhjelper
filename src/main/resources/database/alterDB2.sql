-- 02. Delete foreign key from beneficiaries
ALTER TABLE beneficiaries DROP CONSTRAINT fk_status_beneficiary;

-- 03. Delete column status_id from beneficiaries
ALTER TABLE beneficiaries
  DROP COLUMN status_id;

