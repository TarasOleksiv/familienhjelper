-- 06. Add columns to the table beneficiaries
ALTER TABLE beneficiaries
  ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE
;

-- 07. Add columns to the table beneficiaries
ALTER TABLE projects
  ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE
;
