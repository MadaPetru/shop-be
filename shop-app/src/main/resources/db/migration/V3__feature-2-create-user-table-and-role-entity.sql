CREATE TABLE IF NOT EXISTS "user"
(
    id       bigint NOT NULL
        PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);

ALTER TABLE "user"
    owner TO adi;

CREATE TABLE IF NOT EXISTS "role"
(
    id   bigint NOT NULL
        PRIMARY KEY,
    granted_role VARCHAR(255)
);

ALTER TABLE role
    owner TO adi;

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id bigint NOT NULL
        CONSTRAINT fk_user_roles_user_id
            REFERENCES "user",
    role_id bigint NOT NULL
        CONSTRAINT fk_user_roles_role_id
        REFERENCES "role"
);