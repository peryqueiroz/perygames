CREATE TABLE IF NOT EXISTS bet (
    "id" VARCHAR(26) NOT NULL,
    "user_id" VARCHAR(26) NULL,
    "match_id" varchar(26) NULL,
    "nick" varchar(255) NULL,
    "smurf" BOOLEAN NULL,
    "ranking" INT NULL,
    "medal" varchar(26) NULL,
    "avatar" varchar(255) NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
);

ALTER TABLE user_bet DROP CONSTRAINT IF EXISTS user_bet__player_fk;
ALTER TABLE user_bet ADD CONSTRAINT user_bet__player_fk FOREIGN KEY ("player_id") REFERENCES player("id");
