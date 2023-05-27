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
        CONSTRAINT fki8jnqq05sk5nkma3pfp3ylqrt
            REFERENCES product,
    images_id  bigint NOT NULL
        CONSTRAINT uk_3701am6d8us1lbn5v3j75yinr
            UNIQUE
        CONSTRAINT fkd99dtqyhwdbe0ngde824r2ogi
            REFERENCES image
);