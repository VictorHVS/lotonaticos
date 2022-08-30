package quality

apply<JacocoPlugin>()
apply(plugin = "jacoco")
//
//val jacocoTask = tasks.register<JacocoReport>("jacocoTestReport") {
//    dependsOn(tasks.named("testDebugUnitTest"))
//
//    reports {
//        html.apply {
//            isEnabled = true
//            destination = file("$buildDir/reports/code-coverage/html")
//        }
//        xml.isEnabled = true
//        csv.isEnabled = false
//    }
//
//    val fileFilter = listOf(
//        "**/R.class",
//        "**/R\$*.class",
//        "**/BuildConfig.*",
//        "**/Manifest*.*",
//        "**/*Test*.*",
//        "android/**/*.*"
//    )
//    val debugTree = fileTree("${project.buildDir}/intermediates/javac/debug") {
//        exclude(fileFilter)
//    }
//
//    val mainSrc = "${project.projectDir}/src/main/java"
//
//    sourceDirectories.setFrom(files(listOf(mainSrc)))
//    classDirectories.setFrom(files(listOf(debugTree)))
//    executionData.setFrom(
//        fileTree(project.buildDir) {
//            include(listOf("jacoco/testDebugUnitTest.exec"))
//        }
//    )
//}
//
//tasks.getByName("check").finalizedBy("jacocoTestReport")
//
//tasks.withType(Test::class) {
//    configure<JacocoTaskExtension> {
//        isIncludeNoLocationClasses = true
//        excludes = listOf("jdk.internal.*")
//    }
//}
//

tasks.withType(Test::class) {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

private val classDirectoriesTree = fileTree("${project.buildDir}") {
    include(
        "**/classes/**/main/**",
        "**/intermediates/classes/debug/**",
        "**/intermediates/javac/debug/*/classes/**", // Android Gradle Plugin 3.2.x support.
        "**/tmp/kotlin-classes/debug/**"
    )
    exclude(
        "**/R.class",
        "**/R\$*.class",
        "**/*\$1*",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/models/**",
        "**/*\$Lambda$*.*",
        "**/*\$inlined$*.*"
    )
}

private val sourceDirectoriesTree = files("$projectDir/src/main/java")

private val executionDataTree = fileTree("${project.buildDir}") {
    include(
        "outputs/code_coverage/**/*.ec",
        "jacoco/jacocoTestReportDebug.exec",
        "jacoco/testDebugUnitTest.exec",
        "jacoco/test.exec"
    )
}

fun JacocoReportsContainer.reports() {
    csv.isEnabled = false
    xml.apply {
        isEnabled = true
        destination = file("$buildDir/reports/code-coverage/xml")
    }
    html.apply {
        isEnabled = true
        destination = file("$buildDir/reports/code-coverage/html")
    }
}

fun JacocoReport.setDirectories() {
    sourceDirectories.setFrom(sourceDirectoriesTree)
    classDirectories.setFrom(classDirectoriesTree)
    executionData.setFrom(executionDataTree)
}

fun JacocoCoverageVerification.setDirectories() {
    sourceDirectories.setFrom(sourceDirectoriesTree)
    classDirectories.setFrom(classDirectoriesTree)
    executionData.setFrom(executionDataTree)
}

val jacocoGroup = "verification"
tasks.register<JacocoReport>("jacocoTestReport") {
    group = jacocoGroup
    description = "Code coverage report for both Android and Unit tests."
    dependsOn("testDebugUnitTest")
    reports {
        reports()
    }
    setDirectories()
}

val minimumCoverage = "0.8".toBigDecimal()
tasks.register<JacocoCoverageVerification>("jacocoCoverageVerification") {
    group = jacocoGroup
    description = "Code coverage verification for Android both Android and Unit tests."
    dependsOn("testDebugUnitTest")
    violationRules {
        rule {
            limit {
                minimum = minimumCoverage
            }
        }
        rule {
            element = "CLASS"
            excludes = listOf(
                "**.FactorFacade.Builder",
                "**.ServiceFacade.Builder",
                "**.ChallengeFacade.Builder",
                "**.Task"
            )
            limit {
                minimum = minimumCoverage
            }
        }
    }
    setDirectories()
}