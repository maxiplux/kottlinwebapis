package com.domain.kottlinwebapis.Controllers

/**
 * User: franc
 * Date: 03/09/2018
 * Time: 1:40
 */
import com.domain.kottlinwebapis.models.Comment
import com.domain.kottlinwebapis.models.NewComment
import org.springframework.web.bind.annotation.*
import java.time.Instant


@RestController
class AppController {


    @RequestMapping("/")
    fun index() = "This is home!"


    // New route to hand comment request
    @RequestMapping("/xcomment")
    fun getComment(): Comment {
        val comment = Comment(
                author = "codebeast",
                content = "I'm so loving Kotlin",
                created = Instant.now()
        )
        return comment
    }

    @RequestMapping(value = "/xcomment", method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody newComment: NewComment): Comment {
        val comment = Comment(
                author = newComment.author,
                content = newComment.content,
                created = Instant.now()
        )
        return comment
    }

    //...
    @RequestMapping("/search")
    fun search(@RequestParam(name = "name") value: String): String = value

    @RequestMapping("/xcomment/{value}")
    fun findComment(@PathVariable("value") value: String): String = value

}

