--TODO: use schema
CREATE SCHEMA main;

CREATE TABLE IF NOT EXISTS quote(
    id UUID DEFAULT random_uuid(),
    isin VARCHAR(12) NOT NULL,
    bid DECIMAL NOT NULL,
    ask DECIMAL NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE INDEX quote_created_at_idx ON quote (created_at);