CREATE TABLE IF NOT EXISTS post (
    id VARCHAR(32) NOT NULL,
    title VARCHAR(254),
    body CLOB,
    author VARCHAR(254),
    PRIMARY KEY (id)
);
