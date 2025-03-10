CREATE TABLE IF NOT EXISTS match (
    "id" VARCHAR(26) NOT NULL,
    "game_id" VARCHAR(26) NULL,
    "player_id" VARCHAR(26) NULL,
    "win" BOOLEAN NULL,
    "kill" INT NULL,
    "death" INT NULL,
    "assist" INT NULL,
    "award" varchar(26) NULL,
    "imp" INT NULL,
    "hero" VARCHAR(26) NULL,
    "start_date" TIMESTAMP WITH TIME ZONE,
    "duration" INT NULL,
    "damage_tower" VARCHAR(26) NULL,
    "damage_hero" VARCHAR(26) NULL,
    "healing" VARCHAR(26) NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
);


ALTER TABLE match DROP CONSTRAINT IF EXISTS match__player_fk;
ALTER TABLE match ADD CONSTRAINT match__player_fk FOREIGN KEY ("player_id") REFERENCES player("id");