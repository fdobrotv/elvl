CREATE TABLE IF NOT EXISTS main.quote(
    id UUID DEFAULT random_uuid(),
    isin VARCHAR(12) NOT NULL,
    bid DECIMAL NOT NULL,
    ask DECIMAL NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);