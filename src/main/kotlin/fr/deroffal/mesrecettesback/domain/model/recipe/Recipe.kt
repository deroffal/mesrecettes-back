package fr.deroffal.mesrecettesback.domain.model.recipe

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
data class Recipe(
    var name: String,
    var description: String? = null,
//    @CreatedDate //TODO
    var creationDate: Instant = Instant.now(),
    var dishType: DishType,
    var source: Source,
    var webSource: String
) {

    @Id
    lateinit var id: String
}
