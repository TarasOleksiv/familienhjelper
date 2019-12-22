-- 07. Create table income_types
CREATE TABLE income_types (
  id UUID NOT NULL,
  name VARCHAR(48),
  PRIMARY KEY (id)
)
;

-- 08. Create table currency
CREATE TABLE currency (
  id UUID NOT NULL,
  name VARCHAR(3),
  PRIMARY KEY (id)
)
;