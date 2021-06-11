package fr.deroffal.mesrecettesback.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

@Table
data class Recipe(
    var name: String,
    var description: String? = null,
    var creationDate: Instant = Instant.now(),
    @Enumerated(STRING) var dishType: DishType,
    @Enumerated(STRING) var source: Source,
    var webSource: String
) {

    @Id
    lateinit var id: UUID
}
