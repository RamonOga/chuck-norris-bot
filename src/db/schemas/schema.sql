CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    userId    BIGINT NOT NULL,
    firstName VARCHAR(100),
    isBot     BOOLEAN,
    lastName  VARCHAR(100),
    userName  VARCHAR(100)
);

CREATE TABLE chat
(
    id          SERIAL PRIMARY KEY,
    charId      BIGINT NOT NULL,
    messageType VARCHAR(100)
);

CREATE TABLE photo
(
    id           SERIAL PRIMARY KEY,
    photoId      VARCHAR(100) NOT NULL,
    fileUniqueId VARCHAR(100) NOT NULL,
    width        INTEGER,
    height       INTEGER,
    fileSize     INTEGER,
    filePath     VARCHAR(100),
    bytes        BYTEA
);

CREATE TABLE video
(
    id            SERIAL PRIMARY KEY,
    videoId       VARCHAR(100) NOT NULL,
    fileUniqueId  VARCHAR(100) NOT NULL,
    width         INTEGER,
    height        INTEGER,
    duration      INTEGER,
    thumbPhoto_id INTEGER references photo (id) on delete cascade,
    mimeType      VARCHAR(100),
    fileSize      BIGINT,
    fileName      VARCHAR(100),
    bytes         BYTEA
);

CREATE TABLE messages
(
    id          SERIAL PRIMARY KEY,
    messageId   INTEGER NOT NULL,
    user_id     INTEGER references users (id) on delete cascade,
    date        INTEGER,
    chat_id     INTEGER references chat (id) on delete cascade,
    text        TEXT,
    photo_id    INTEGER references photo (id) on delete cascade,
    video_id    INTEGER references video (id) on delete cascade,
    caption     TEXT,
    messageType VARCHAR(50)
);