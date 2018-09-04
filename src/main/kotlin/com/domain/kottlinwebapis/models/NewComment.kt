package com.domain.kottlinwebapis.models

import com.fasterxml.jackson.annotation.JsonCreator

data class NewComment @JsonCreator constructor(
        val author: String,
        val content: String
)
