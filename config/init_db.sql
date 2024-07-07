CREATE TABLE IF NOT EXISTS resume
(
    uuid      CHAR(5) PRIMARY KEY NOT NULL,
    full_name TEXT                 NOT NULL
);

CREATE TABLE IF NOT EXISTS contact
(
    id          SERIAL PRIMARY KEY,
    resume_uuid CHAR(5)            NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT                NOT NULL,
    value       TEXT                NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS contact_uuid_type_index
    ON contact (resume_uuid, type);