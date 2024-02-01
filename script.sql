CREATE SCHEMA IF NOT EXISTS contact_list;

CREATE TABLE IF NOT EXISTS contact_list.contacts
(
    id        BIGINT NOT NULL PRIMARY KEY,
    firstName VARCHAR(55) NOT NULL,
    lastName  VARCHAR(55),
    email     VARCHAR(55),
    phone     varchar(55) NOT NULL
)