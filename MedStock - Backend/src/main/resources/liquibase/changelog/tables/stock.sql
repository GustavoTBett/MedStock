DROP TABLE IF EXISTS stock;

CREATE TABLE stock (
    id bigserial NOT NULL,
    created timestamp(6) NULL default now(),
    version int4 not null default 0,
    product_id int4 null,
    batch_id int4 null,
    quantity int4 null,

    CONSTRAINT batch_pkey PRIMARY KEY (id)
);

ALTER TABLE stock ADD CONSTRAINT fk_stock__product FOREIGN KEY (product_id) REFERENCES product(id);
ALTER TABLE stock ADD CONSTRAINT fk_batch__product FOREIGN KEY (batch_id) REFERENCES batch(id);