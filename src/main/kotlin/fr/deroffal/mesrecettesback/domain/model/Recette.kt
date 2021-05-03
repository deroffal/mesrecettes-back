package fr.deroffal.mesrecettesback.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table
data class Recette(
    var nom: String,
    var description: String,
    var dateCreation: Instant = Instant.now()
) {

    @Id
    lateinit var id: UUID
}
