-- 09. Populate currency_rates;
INSERT INTO currency_rates (id, sourceCurrency, targetCurrency, rate) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'EUR', 9.82),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'NOK', 1),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'RUB', 0.14),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'UAH', 0.37),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'NOK', 'USD', 8.80)
;
