CREATE TABLE users(
    id UUID DEFAULT RANDOM_UUID() primary key,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(255) NOT NULL,
    group_id UUID,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY(group_id) REFERENCES groups(id)
);
