CREATE TABLE IF NOT EXISTS contacts
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY
        PRIMARY KEY,
    first_name VARCHAR(256) NOT NULl,
    last_name  VARCHAR(256) NOT NULL,
    email      VARCHAR(256) NOT NULL
        UNIQUE,
    phone      VARCHAR(256) NOT NULL
        UNIQUE
);