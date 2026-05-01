CREATE TABLE usuario (
    id       BIGSERIAL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    role     VARCHAR(50)  NOT NULL,
    username VARCHAR(150) NOT NULL
);