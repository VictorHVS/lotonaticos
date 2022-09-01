plugins {
    id("quality.detekt")
    id("quality.ktlint")
    id("quality.jacoco")
    id("quality.sonarqube")
}

tasks.getByName("check") {
    setDependsOn(
        listOf(
            tasks.getByName("ktlint"),
            tasks.getByName("detekt")
        )
    )
}
