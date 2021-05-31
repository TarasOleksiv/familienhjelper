-- 17. Add column to the table projects
ALTER TABLE projects
  ADD COLUMN expense DECIMAL(11,2) DEFAULT 0.00 NULL
;
