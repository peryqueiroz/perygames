CREATE TABLE IF NOT EXISTS player (
    "id" VARCHAR(26) NOT NULL,
    "game_id" VARCHAR(26) NULL,
    "name" varchar(26) NOT NULL,
    "nick" varchar(255) NULL,
    "smurf" BOOLEAN NULL,
    "ranking" INT NULL,
    "medal" varchar(26) NULL,
    "avatar" varchar(255) NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
);