package quality

import org.sonarqube.gradle.SonarQubeExtension

apply<SonarqubePlugin>()

val codeExclusions = listOf(
    "**/R.*",
    "**/R$*.*",
    "**/BuildConfig.*",
)

val coverageExclusions = listOf(
    // App
    "**/LotonaticosApp.kt",
    "**/initializers/**",

    // Common
    "**/common/**",

    // Common Android
    "**Activity.kt",
    "**Fragment.kt",
    "**/base/**",
    "**/navigation/**",

    // Hilt
    "**/di/**",

    // Ui
    "**/ui/**/components/**",
    "**/ui/**/view/**",
)

configure<SonarQubeExtension> {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "victorhvs")
        property("sonar.projectKey", "VictorHVS_lotonaticos")
        property("sonar.projectName", "Lotonáticos")
//        property("sonar.projectVersion", "${VersionName}_(${VersionCode})")

        property("sonar.pullrequest.provider", "GitHub")
        property("sonar.pullrequest.github.repository", "VictorHVS/lotonaticos")

        property("sonar.coverage.exclusions", coverageExclusions.joinToString(separator = ","))
        property("sonar.exclusions", codeExclusions.joinToString(separator = ","))
        property("sonar.java.coveragePlugin", "jacoco")
//        property(
//            "sonar.kotlin.detekt.reportPaths",
//            "${rootProject.buildDir}/reports/detekt/detekt.xml"
//        )
        property("sonar.junit.reportPaths", "build/test-results/**/*.xml")
        property("sonar.jacoco.reportPaths", "build/reports/jacoco/**/*.xml")
        property("sonar.kotlin.detekt.reportPaths", "build/reports/detekt/detekt.xml")
        property("sonar.androidLint.reportPaths", "build/reports/lint-results-debug.xml")

        property("sonar.language", "kotlin")
        property("sonar.log.level", "TRACE")
        property("sonar.qualitygate.wait", true)
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.tags", "android")
        property("sonar.verbose", true)
    }

    subprojects {
        androidVariant = "debug"
    }
}
