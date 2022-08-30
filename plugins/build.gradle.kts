plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
//    implementation("com.android.tools.build:gradle:7.3.0-beta05")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    implementation(libs.detekt)
}
