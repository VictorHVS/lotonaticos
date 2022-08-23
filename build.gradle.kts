//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//        maven(url = "https://jitpack.io")
//    }
//    dependencies {
//        classpath(Dependencies.Plugin.androidGradlePlugin)
//        classpath(Dependencies.Plugin.kotlinPlugin)
//        classpath(libs.plugins.hilt)
//        classpath(Dependencies.Plugin.kotlinSerialization)
//        classpath(Dependencies.Plugin.googleServices)
//        classpath(Dependencies.Plugin.crashlytics)
//         NOTE: Do not place your application dependencies here; they belong
//         in the individual module build.gradle files
//    }
//}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.junit) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlinKapt) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktLint) apply false
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
