package com.b1thouse.perygames.domain.entities

import de.huxhorn.sulky.ulid.ULID

class BetDetail(
    val id: String = ULID().nextULID(),
    val betId: String,
    val betSubtypeId: String
)