package fr.deroffal.mesrecettesback.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

@Table
data class Recette(
    var nom: String,
    var description: String,
    var dateCreation: Instant = Instant.now(),
    @Enumerated(STRING) var typePlat: TypePlat
) {

    @Id
    lateinit var id: UUID
}
