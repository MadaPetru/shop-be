CREATE TABLE image
(
    id   bigint NOT NULL
        PRIMARY KEY,
    name VARCHAR(255),
    path VARCHAR(255)
);

ALTER TABLE image
    owner TO adi;

CREATE TABLE product
(
    id       bigint NOT NULL
        PRIMARY KEY,
    name     VARCHAR(255),
    price    REAL,
    quantity INTEGER
);

ALTER TABLE product
    owner TO adi;

CREATE TABLE product_images
(
    product_id bigint NOT NULL
        CONSTRAINT product_id_foreign_key
            REFERENCES product,
    images_id  bigint NOT NULL
        CONSTRAINT image_id_foreign_key
            REFERENCES image
);