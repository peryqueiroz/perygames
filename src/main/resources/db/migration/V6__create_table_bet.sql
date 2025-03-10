CREATE TABLE IF NOT EXISTS bet (
    "id" VARCHAR(26) NOT NULL,
    "user_id" VARCHAR(26) NOT NULL,
    "match_id" varchar(26),
    "status" VARCHAR(26) NOT NULL,
    "amount_bet" DECIMAL(10,2),
    "amount_return" DECIMAL(10,2),
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
);

ALTER TABLE bet DROP CONSTRAINT IF EXISTS bet__user_bet_fk;
ALTER TABLE bet ADD CONSTRAINT bet__user_bet_fk FOREIGN KEY ("user_id") REFERENCES user_bet("id");

ALTER TABLE bet DROP CONSTRAINT IF EXISTS bet__match_fk;
ALTER TABLE bet ADD CONSTRAINT bet__match_fk FOREIGN KEY ("match_id") REFERENCES match("id");