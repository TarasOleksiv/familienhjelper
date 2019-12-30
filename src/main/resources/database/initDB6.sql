-- 11. Create table project_member
CREATE TABLE project_member (
                       id UUID NOT NULL,
                        project_id UUID NOT NULL,
                        member_id UUID NOT NULL,
                        pledge DECIMAL(11,2) NULL,
                       startPledge DATE NULL,
                       stopPledge DATE NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT FK_PROJECT_ID FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                       CONSTRAINT FK_MEMBER_ID FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE NO ACTION ON UPDATE NO ACTION
                       )
;