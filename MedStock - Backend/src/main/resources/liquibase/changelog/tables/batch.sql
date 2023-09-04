DROP TABLE IF EXISTS batch;

CREATE TABLE batch (
    id bigserial NOT NULL,
    created timestamp(6) NULL default now(),
    version int4 not null default 0,
    number int4 not null,
    fabrication_date date NULL,
    valid_date date null,
    product_id int4 null,

    CONSTRAINT batch_pkey PRIMARY KEY (id)
);

ALTER TABLE batch ADD CONSTRAINT fk_batch__product FOREIGN KEY (product_id) REFERENCES product(id);