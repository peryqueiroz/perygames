CREATE TABLE transaction_record (
    "id" VARCHAR(26) NOT NULL,
    "user_id" VARCHAR(26) NOT NULL,
    "bet_id" VARCHAR(26),
    "transaction_type" VARCHAR(50) NOT NULL,
    "amount" DECIMAL(10, 2) NOT NULL,
    "balance_after_transaction" DECIMAL(10, 2) NOT NULL,
    "description" VARCHAR(255),
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY("id")
    );

    ALTER TABLE transaction_record DROP CONSTRAINT IF EXISTS transaction_record__user_bet_fk;
    ALTER TABLE transaction_record ADD CONSTRAINT transaction_record__user_bet_fk FOREIGN KEY ("user_id") REFERENCES user_bet("id");

    ALTER TABLE transaction_record DROP CONSTRAINT IF EXISTS transaction_record__bet_fk;
    ALTER TABLE transaction_record ADD CONSTRAINT transaction_record__bet_fk FOREIGN KEY ("bet_id") REFERENCES bet("id");
