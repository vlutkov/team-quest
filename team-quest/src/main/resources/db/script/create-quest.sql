CREATE TABLE quest (
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(32) UNIQUE NOT NULL,
    type SMALLINT NOT NULL,
    age SMALLINT,
    person_num SMALLINT,
    complexity SMALLINT,
    rating REAL,
    start_price DOUBLE PRECISION,
    transit_time SMALLINT,
    description TEXT,
    create_date TIMESTAMP NOT NULL DEFAULT NOW()::timestamp,
    update_date TIMESTAMP
);