package fr.deroffal.mesrecettesback.domain.services

import fr.deroffal.mesrecettesback.domain.model.Recette
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class RecetteService(var recetteRepository: RecetteRepository) {

    fun findById(id: UUID) = recetteRepository.findById(id)

    fun save(recette: Recette): Mono<Recette> = recetteRepository.save(recette)

    fun findAll() = recetteRepository.findAll()
}
