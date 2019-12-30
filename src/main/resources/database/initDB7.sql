-- 12. Create table images
CREATE TABLE images (
                       id UUID NOT NULL,
                       project_id UUID NOT NULL,
                       description VARCHAR(2000) NULL,
                       link VARCHAR(1000) NOT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT FK_PROJECT_ID FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE NO ACTION ON UPDATE NO ACTION
                       )
;