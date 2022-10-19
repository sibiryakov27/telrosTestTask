CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    second_name VARCHAR(30),
    first_name VARCHAR(30) NOT NULL,
    patronymic VARCHAR(30),
    birth_date DATE,
    email VARCHAR(50),
    phone_number VARCHAR(10)
);
