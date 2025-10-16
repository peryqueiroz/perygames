package com.b1thouse.perygames.application.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserBetStatus(
    @JsonProperty("hasBetPending ", required = true) val hasBetPending : Boolean,
)