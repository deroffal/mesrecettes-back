package fr.deroffal.mesrecettesback

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@SpringBootApplication
@EnableReactiveMongoRepositories
class MesrecettesBackApplication

fun main(args: Array<String>) {
    runApplication<MesrecettesBackApplication>(*args)
}

////@Configuration
//@EnableReactiveMongoRepositories
//class MongoReactiveApplication : AbstractReactiveMongoConfiguration() {
//
//    @Bean
//    fun mongoClient(): MongoClient = MongoClients.create()
//
//    override fun getDatabaseName() = "mesrecettesdb"
//}
