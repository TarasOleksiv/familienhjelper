-- 04. Create table members;
CREATE TABLE members (
  id UUID NOT NULL,
  name VARCHAR(48) NOT NULL,
  email VARCHAR(96) NULL,
  mobile VARCHAR(25) NULL,
  city VARCHAR(96) NULL,
  account VARCHAR(255) NULL,
  bank VARCHAR(96) NULL,
  PRIMARY KEY (id))
  ;

-- 05. Create table statuses
CREATE TABLE statuses (
  id UUID NOT NULL,
  name VARCHAR(48),
  PRIMARY KEY (id)
)
  ;

-- 06. Create table beneficiaries
CREATE TABLE beneficiaries (
  id UUID NOT NULL,
  name VARCHAR(48) NOT NULL,
  family VARCHAR(480) NULL,
  income DECIMAL(11,2) NULL,
  description VARCHAR(2000) NULL,
  datafield DATE,
  status_id UUID NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_STATUS_BENEFICIARY FOREIGN KEY (status_id) REFERENCES statuses (id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
  ;

