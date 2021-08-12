package fr.deroffal.mesrecettesback

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices


@AnalyzeClasses(
    packagesOf = [ArchitectureTest::class],
    packages = ["org.springframework.data.mongodb"]
)
internal class ArchitectureTest {

    @ArchTest
    var enforceHexagonalArchitechture: ArchRule = classes().that()
        .resideInAPackage("$DOMAIN_PACKAGE..")
        .should()
        .onlyAccessClassesThat()
        .resideInAnyPackage(
            "$DOMAIN_PACKAGE..",
            "kotlin.jvm..",
            "kotlin.collections..",
            "java.util..",
            "java.time..",
            "java.lang.."
        )

    @ArchTest
    val `No package cycles` =
        slices().matching("$BASE_PACKAGE.(**)..")
            .should().beFreeOfCycles()

    @ArchTest
    val `the domain model does not have outgoing dependencies` =
        noClasses().that().resideInAPackage("$DOMAIN_MODEL_PACKAGE..")
            .should().accessClassesThat().resideInAnyPackage("$DOMAIN_SERVICE_PACKAGE..", "$APPLICATION_PACKAGE..", "$ADAPTER_PACKAGE..")


    @ArchTest
    val `the domain does not access the application and adapters` =
        noClasses().that().resideInAPackage("$DOMAIN_PACKAGE..")
            .should().accessClassesThat().resideInAnyPackage("$APPLICATION_PACKAGE..", "$ADAPTER_PACKAGE..")

    @ArchTest
    val `the application does not access the adapters` =
        noClasses().that().resideInAPackage("$APPLICATION_PACKAGE..")
            .should().accessClassesThat().resideInAPackage("$ADAPTER_PACKAGE..")

    @ArchTest
    val `one adapter should not access another adapter` =
        slices().matching("$ADAPTER_PACKAGE.(*)")
            .should().notDependOnEachOther()

    @ArchTest
    val `mongo should only be used in database adapter` = Architectures.layeredArchitecture()
            .layer("mongoDb").definedBy("org.springframework.data.mongodb..")
            .layer("databaseAdapter").definedBy("fr.deroffal.mesrecettesback.adapter.database..")
        .whereLayer("mongoDb").mayOnlyBeAccessedByLayers("databaseAdapter")

    companion object {
        private val BASE_PACKAGE = ArchitectureTest::class.java.`package`.name

        private val DOMAIN_PACKAGE = "$BASE_PACKAGE.domain"
        private val DOMAIN_MODEL_PACKAGE = "$DOMAIN_PACKAGE.model"
        private val DOMAIN_SERVICE_PACKAGE = "$DOMAIN_PACKAGE.service"

        private val APPLICATION_PACKAGE = "$BASE_PACKAGE.application"

        private val ADAPTER_PACKAGE = "$BASE_PACKAGE.adapter"
    }

}
