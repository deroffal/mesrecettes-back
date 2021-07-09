package fr.deroffal.mesrecettesback.adapter.routing

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicate
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

private fun String.andOptionalSlash(): RequestPredicate = path(this).or(path("$this/"))

@Configuration
class Routers(
    private val recipeHandler: RecipeHandler,
    private val administrationHandler: AdministrationHandler
) {

    @Bean
    fun recipeRoutes(): RouterFunction<ServerResponse> = router {
        ("/recette" and accept(MediaType.APPLICATION_JSON)).nest {
            GET("".andOptionalSlash(), recipeHandler::list)
            POST("/", recipeHandler::createRecette)
            GET("/{id}".andOptionalSlash(), recipeHandler::getRecette)
        }
    }

    @Bean
    fun adminRoutes(): RouterFunction<ServerResponse> = router {
        "/admin".nest {
            POST("/init".andOptionalSlash(), administrationHandler::init)
        }
    }

}
