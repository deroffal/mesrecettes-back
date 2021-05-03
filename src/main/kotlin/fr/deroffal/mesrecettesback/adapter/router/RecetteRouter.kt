package fr.deroffal.mesrecettesback.adapter.router


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
class RecetteRouter {

    @Bean
    fun route(recetteHandler: RecetteHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(
                GET("/recette/{id}").and(accept(APPLICATION_JSON)), recetteHandler::getRecette
            ).andRoute(
                POST("/recette").and(accept(APPLICATION_JSON)), recetteHandler::createRecette
            )
    }
}
