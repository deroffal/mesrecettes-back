package fr.deroffal.mesrecettes.back.adapter.database.recipe

import fr.deroffal.mesrecettes.back.domain.model.recipe.DishType
import fr.deroffal.mesrecettes.back.domain.model.recipe.Source
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class RecipeEntity(
    var name: String,
    var description: String? = null,

    var creationDate: Instant = Instant.now(),
    var dishType: DishType,
    var source: Source,
    var webSource: String
) {

    @Id
    lateinit var id: String
}
