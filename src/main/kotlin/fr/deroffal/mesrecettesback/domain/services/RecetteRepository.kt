package fr.deroffal.mesrecettesback.domain.services

import fr.deroffal.mesrecettesback.domain.model.Recette
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*


interface RecetteRepository : ReactiveCrudRepository<Recette, UUID>

