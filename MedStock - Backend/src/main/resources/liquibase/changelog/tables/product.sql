DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id bigserial NOT NULL,
    created timestamp(6) NULL default now(),
    version int4 not null default 0,
    name varchar(255) not null,
    description varchar(1024) NULL,
    code int4 NOT NULL,
    category varchar(255) NULL,

    CONSTRAINT product_pkey PRIMARY KEY (id)
);