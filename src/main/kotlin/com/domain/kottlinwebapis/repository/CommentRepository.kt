package com.domain.kottlinwebapis.repository

/**
 * User: franc
 * Date: 03/09/2018
 * Time: 2:03
 */

import com.domain.kottlinwebapis.models.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface CommentRepository : CrudRepository<Comment, Long>
