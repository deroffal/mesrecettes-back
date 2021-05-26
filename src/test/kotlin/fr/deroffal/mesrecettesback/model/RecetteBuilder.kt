package fr.deroffal.mesrecettesback.model

import fr.deroffal.mesrecettesback.domain.model.Recette
import fr.deroffal.mesrecettesback.domain.model.Source
import fr.deroffal.mesrecettesback.domain.model.TypePlat
import java.time.Instant

data class RecetteBuilder(
    var nom: String = "nom recette",
    var description: String = "description recette",
    var dateCreation: Instant = Instant.now(),
    var typePlat: TypePlat = TypePlat.PLAT,
    var source: Source = Source.WEB,
    var sourceWeb: String = "https://www.marmiton.org/maRecette"
) {
    fun build() = Recette(
        nom = nom,
        description = description,
        dateCreation = dateCreation,
        typePlat = typePlat,
        source = source,
        sourceWeb = sourceWeb
    )
}
