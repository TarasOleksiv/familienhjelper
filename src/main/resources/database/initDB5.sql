-- 10. Create table projects
CREATE TABLE projects (
                       id UUID NOT NULL,
                       name VARCHAR(45) NOT NULL,
                       description VARCHAR(2000) NULL,
                       startDate DATE NULL,
                       stopDate DATE NULL,
                       feedback VARCHAR(2000) NULL,
                       balance DECIMAL(11,2) DEFAULT 0.00 NULL,
                       fieldContact_id UUID,
                       fu_id UUID,
                       status_id UUID,
                       PRIMARY KEY (id),
                       UNIQUE (name),
                       CONSTRAINT FK_FIELDCONTACT_ID FOREIGN KEY (fieldContact_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                       CONSTRAINT FK_FU_ID FOREIGN KEY (fu_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                       CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id) REFERENCES statuses (id) ON DELETE NO ACTION ON UPDATE NO ACTION
                      )
;