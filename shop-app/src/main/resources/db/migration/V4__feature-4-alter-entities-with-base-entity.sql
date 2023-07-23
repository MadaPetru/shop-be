ALTER TABLE "user"
    ADD COLUMN created_timestamp timestamp;
ALTER TABLE "user"
    ADD COLUMN updated_timestamp timestamp;
ALTER TABLE "user"
    ADD COLUMN version BIGINT;

ALTER TABLE product
    ADD COLUMN created_timestamp timestamp;
ALTER TABLE product
    ADD COLUMN updated_timestamp timestamp;
ALTER TABLE product
    ADD COLUMN version BIGINT;

ALTER TABLE image
    ADD COLUMN created_timestamp timestamp;
ALTER TABLE image
    ADD COLUMN updated_timestamp timestamp;
ALTER TABLE image
    ADD COLUMN version BIGINT;