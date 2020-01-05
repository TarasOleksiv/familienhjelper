-- 11. Add column to the table transaction_types
ALTER TABLE transaction_types
  ADD COLUMN isDonation BOOLEAN
;

ALTER TABLE transactions DROP CONSTRAINT fk_type_id;

DELETE FROM transaction_types;

INSERT INTO transaction_types (id, name, isdonation) VALUES
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Transfer', TRUE),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Cash', FALSE),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Food', FALSE),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Medical', FALSE),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Repair', FALSE),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Field expense', FALSE),
  (uuid_in(md5(random()::text || now()::text)::cstring), 'Other', FALSE)
;

/*UPDATE transaction_types SET isdonation = TRUE
WHERE name IN ('Transfer')
;

UPDATE transaction_types SET isdonation = FALSE
WHERE name IN ('Cash','Food','Medical','Repair','Field expense','Other')
;*/

-- update typeId in transactions
UPDATE transactions SET typeid = (SELECT id FROM transaction_types WHERE name = 'Cash')
;

ALTER TABLE transactions
  ADD CONSTRAINT FK_TYPE_ID FOREIGN KEY (typeId) REFERENCES transaction_types (id) ON DELETE NO ACTION ON UPDATE NO ACTION
;
