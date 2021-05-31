-- 13. Add column to the table beneficiaries
ALTER TABLE beneficiaries
  ADD COLUMN expense DECIMAL(11,2) DEFAULT 0.00 NULL
;
