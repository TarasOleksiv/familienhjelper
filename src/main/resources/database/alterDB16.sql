-- 16. Add column to the table projects
ALTER TABLE projects
  ADD COLUMN helper_id UUID,
  ADD CONSTRAINT FK_HELPER_ID FOREIGN KEY (helper_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION
;
