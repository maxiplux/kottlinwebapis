package com.domain.kottlinwebapis

import com.domain.kottlinwebapis.models.Comment
import com.domain.kottlinwebapis.repository.CommentRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Instant


@SpringBootApplication
class KottlinwebapisApplication {
    @Bean
    fun init(repository: CommentRepository) = CommandLineRunner {


        repository.save(Comment(
                author = "Juan",
                content = "el creado de contenidos",
                created = Instant.now()
        ))
    }
}


fun main(args: Array<String>) {
    runApplication<KottlinwebapisApplication>(*args)
}
