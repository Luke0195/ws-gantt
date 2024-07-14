CREATE TABLE groups(
    id UUID DEFAULT RANDOM_UUID() primary key,
    name VARCHAR(255) NOT NULL,
    project_id UUID,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    foreign key(project_id) references projects(id) ON UPDATE CASCADE ON DELETE SET NULL
);