CREATE TABLE IF NOT EXISTS user_bet (
    "id" VARCHAR(26) NOT NULL,
    "player_id" VARCHAR(26),
    "status" varchar(26),
    "email" varchar(255) NOT NULL,
    "password" varchar(26) NOT NULL,
    "balance" decimal(10,2) NOT NULL DEFAULT 0.00,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
);

ALTER TABLE user_bet DROP CONSTRAINT IF EXISTS user_bet__player_fk;
ALTER TABLE user_bet ADD CONSTRAINT user_bet__player_fk FOREIGN KEY ("player_id") REFERENCES player("id");
