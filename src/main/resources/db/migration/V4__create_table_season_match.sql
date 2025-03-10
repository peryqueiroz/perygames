CREATE TABLE IF NOT EXISTS season_match (
    "id" VARCHAR(26) NOT NULL,
    "match_id" VARCHAR(26) NOT NULL,
    "player_id" VARCHAR(26) NOT NULL,
    "season_id" VARCHAR(26) NOT NULL,
    "score" INT NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
);


ALTER TABLE season_match DROP CONSTRAINT IF EXISTS season_match__match_fk;
ALTER TABLE season_match ADD CONSTRAINT season_match__match_fk FOREIGN KEY ("match_id") REFERENCES match("id");

ALTER TABLE season_match DROP CONSTRAINT IF EXISTS season_match__player_fk;
ALTER TABLE season_match ADD CONSTRAINT season_match__player_fk FOREIGN KEY ("player_id") REFERENCES player("id");

ALTER TABLE season_match DROP CONSTRAINT IF EXISTS season_match__season_fk;
ALTER TABLE season_match ADD CONSTRAINT season_match__season_fk FOREIGN KEY ("season_id") REFERENCES season("id");