DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id bigserial NOT NULL,
    created timestamp(6) NULL default now(),
    version int4 not null default 0,
    name varchar(255) unique not null,
    email varchar(1024) unique NOT NULL,
    password varchar(255) NOT NULL,
    role char NOT NULL,

    CONSTRAINT user_pkey PRIMARY KEY (id)
);