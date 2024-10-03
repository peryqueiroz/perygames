CREATE TABLE IF NOT EXISTS bet_detail (
    "id" VARCHAR(26) NOT NULL,
    "bet_id" VARCHAR(26) NULL,
    "bet_type_id" varchar(26) NULL,
    PRIMARY KEY("id")
);

ALTER TABLE bet_detail DROP CONSTRAINT IF EXISTS bet_detail__bet_fk;
ALTER TABLE bet_detail ADD CONSTRAINT bet_detail__bet_fk FOREIGN KEY ("bet_id") REFERENCES bet("id");

ALTER TABLE bet_detail DROP CONSTRAINT IF EXISTS bet_detail__bet_type_fk;
ALTER TABLE bet_detail ADD CONSTRAINT bet_detail__bet_type_fk FOREIGN KEY ("bet_type_id") REFERENCES bet_type("id");