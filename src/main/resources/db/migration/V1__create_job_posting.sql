CREATE TABLE job_postings (
    id  BIGSERIAL PRIMARY KEY,
    company VARCHAR(255),
    title VARCHAR(255),
    location VARCHAR(255),
    external_id VARCHAR(255) UNIQUE,
    url VARCHAR(1600),
    first_seen TIMESTAMP
)