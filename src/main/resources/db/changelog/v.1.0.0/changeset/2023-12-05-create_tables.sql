CREATE TABLE IF NOT EXISTS users
(
    user_id           bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name              character varying(250) NOT NULL,
    surname           character varying(250),
    password          character varying(100) NOT NULL,
    email             character varying(250) NOT NULL UNIQUE,
    date_registration date                   NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks
(
    task_id           bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    author_id         bigint                 NOT NULL REFERENCES users(user_id) ON DELETE SET NULL,
    performer_id      bigint                 NOT NULL REFERENCES users(user_id),
    title             varchar                NOT NULL,
    description       varchar ,
    status            int                    NOT NULL,
    priority          int                    NOT NULL,
    date_registration timestamp              NOT NULL
);

CREATE TABLE IF NOT EXISTS comments
(
    comment_id         bigint                NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id            bigint                NOT NULL,
    task_id            bigint                NOT NULL REFERENCES tasks(task_id) ON DELETE CASCADE,
    date_registration  timestamp             NOT NULL,
    description        varchar               NOT NULL
);