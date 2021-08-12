package fr.deroffal.mesrecettesback

import org.mapstruct.MapperConfig
import org.mapstruct.ReportingPolicy.ERROR
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class MesrecettesBackApplication

fun main(args: Array<String>) {
    runApplication<MesrecettesBackApplication>(*args)
}

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ERROR)
interface MapperConfiguration
