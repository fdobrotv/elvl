CREATE SCHEMA main;

CREATE TABLE main.quote (
    id UUID PRIMARY KEY gen_random_uuid(),
    isin STRING,
    bid DECIMAL,
    ask DECIMAL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    INDEX created_at_idx (created_at DESC),
    INDEX completed_at_idx (updated_at DESC)
);