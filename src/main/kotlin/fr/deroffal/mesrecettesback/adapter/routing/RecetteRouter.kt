package fr.deroffal.mesrecettesback.adapter.routing


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class RecetteRouter(private val recetteHandler: RecetteHandler) {

    @Bean
    fun route() = router {
        ("/recette" and accept(APPLICATION_JSON)).nest {
            GET("/{id}", recetteHandler::getRecette)
            POST("/", recetteHandler::createRecette)
        }

    }
}
