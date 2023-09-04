DROP TABLE IF EXISTS provider;

CREATE TABLE provider (
    id bigserial NOT NULL,
    created timestamp(6) NULL default now(),
    version int4 not null default 0,
    name varchar(255) not null,
    email varchar(1024) NULL,
    phone int4 null,
    cnpj varchar(14) null,
    zipcode varchar(8) null,
    adress varchar(255) null,
    state char null, 

    CONSTRAINT provider_pkey PRIMARY KEY (id)
);