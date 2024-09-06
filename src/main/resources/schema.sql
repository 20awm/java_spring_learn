CREATE TABLE IF NOT EXISTS Event (
    id INT NOT NULL,
    title varchar(250) NOT NULL,
    start_on timestamp NOT NULL,
    complete_on timestamp NOT NULL,
    participant INT NOT NULL,
    location varchar(10) NOT NULL,
    PRIMARY KEY (id)
);