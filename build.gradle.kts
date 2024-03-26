buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.23")
        classpath("com.google.gms:google-services:4.4.1")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("org.jacoco:org.jacoco.core:0.8.11")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}