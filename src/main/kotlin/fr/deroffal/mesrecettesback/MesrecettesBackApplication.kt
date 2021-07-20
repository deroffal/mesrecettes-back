package fr.deroffal.mesrecettesback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@SpringBootApplication
@EnableReactiveMongoRepositories
class MesrecettesBackApplication

fun main(args: Array<String>) {
    runApplication<MesrecettesBackApplication>(*args)
}
