CREATE TABLE IF NOT EXISTS bet_subtype (
    "id" VARCHAR(26) NOT NULL,
    "type_id" VARCHAR(26) NOT NULL,
    "subtype" VARCHAR(26),
    "odd" decimal(5,2),
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
);

ALTER TABLE bet_subtype DROP CONSTRAINT IF EXISTS bet_subtype__bet_type_fk;
ALTER TABLE bet_subtype ADD CONSTRAINT bet_subtype__bet_type_fk FOREIGN KEY ("type_id") REFERENCES bet_type("id");