-- 14. Create table currency_rates
CREATE TABLE currency_rates (
  id UUID NOT NULL,
  sourceCurrency VARCHAR(3) NOT NULL,
  targetCurrency VARCHAR(3) NOT NULL,
  rate DECIMAL(11,2) NOT NULL,
  PRIMARY KEY (id)
)
;