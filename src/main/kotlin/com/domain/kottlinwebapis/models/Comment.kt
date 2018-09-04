package com.domain.kottlinwebapis.models

/**
 * User: franc
 * Date: 03/09/2018
 * Time: 1:39
 */
import java.time.Instant

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        val author: String? = null,
        val content: String? = null,
        val created: Instant? = null

)