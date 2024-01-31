CREATE TABLE IF NOT EXISTS USERS
(
    ID           BIGINT  GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    NAME         VARCHAR                                              NOT NULL,
    EMAIL        VARCHAR UNIQUE                                       NOT NULL
);
CREATE TABLE IF NOT EXISTS TASKS
(
    ID           BIGINT  GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    NAME         VARCHAR                                              NOT NULL,
    DESCRIPTION  VARCHAR                                              NOT NULL,
    OWNER_ID     BIGINT  REFERENCES USERS (id),
    STATUS       VARCHAR
);
CREATE TABLE IF NOT EXISTS KANBANS
(
    ID           BIGINT  GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    NAME         VARCHAR                                              NOT NULL,
    TASK_ID      BIGINT  REFERENCES TASKS (id),
    OWNER_ID     BIGINT  REFERENCES USERS (id)
);