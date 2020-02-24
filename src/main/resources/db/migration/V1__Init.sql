--TODO: use schema
CREATE SCHEMA main;

CREATE TABLE IF NOT EXISTS quote(
    id UUID DEFAULT random_uuid(),
    isin VARCHAR(12) NOT NULL,
    bid DECIMAL NOT NULL,
    ask DECIMAL NOT NULL,
    created_at TIMESTAMP(9) DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE INDEX quote_created_at_idx ON quote (created_at);

--INSERT INTO quote (isin, bid, ask) VALUES ('RU200A0JX0J2', 99.5, 100.9);
--INSERT INTO quote (isin, bid, ask) VALUES ('RU200A0JX0J2', 98.4, 99.8);
--INSERT INTO quote (isin, bid, ask) VALUES ('RU200A0JX0J2', 97.3, 98.7);
--INSERT INTO quote (isin, bid, ask) VALUES ('RU500A0JX0J2', 96.3, 97.7);