CREATE TABLE IF NOT EXISTS image
(
    id   bigint NOT NULL
        PRIMARY KEY,
    name VARCHAR(255),
    path VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product
(
    id       bigint NOT NULL
        PRIMARY KEY,
    name     VARCHAR(255),
    price    REAL,
    quantity INTEGER
);

CREATE TABLE IF NOT EXISTS product_images
(
    product_id bigint NOT NULL
        CONSTRAINT product_id_foreign_key
            REFERENCES product,
    images_id  bigint NOT NULL
        CONSTRAINT image_id_foreign_key
            REFERENCES image
);