package fr.deroffal.mesrecettesback.adapter.routing


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class RecipeRouter(private val recipeHandler: RecipeHandler) {

    @Bean
    fun route() = router {
        ("/recette" and accept(APPLICATION_JSON)).nest {
            GET("/", recipeHandler::list)
            POST("/", recipeHandler::createRecette)
            GET("/{id}", recipeHandler::getRecette)
        }

    }
}
