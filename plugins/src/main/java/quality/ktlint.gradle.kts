package quality

import ktlint
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val ktlint: Configuration by configurations.creating
val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    ktlint(libs.ktlint)
}

tasks {
    register<JavaExec>("ktlint") {

        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args(
            "src/**/*.kt",
            "--reporter=checkstyle,output=$buildDir/reports/ktlint/ktlint-checkstyle.xml",
            "--reporter=plain",
            "--editorconfig=$rootDir/config/filters/ktlint.editorconfig"
        )
    }

    register<JavaExec>("ktlintFormat") {
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args(
            "-F",
            "src/**/*.kt",
            "--editorconfig=$rootDir/config/filters/ktlint.editorconfig"
        )
    }
}

afterEvaluate {
    tasks.named("check") {
        dependsOn(tasks.getByName("ktlint"))
    }
}
