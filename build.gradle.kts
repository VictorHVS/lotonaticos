buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:9.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.60.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:2.4.10")
        classpath("com.google.gms:google-services:4.5.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:3.0.8")
        classpath("org.jacoco:org.jacoco.core:0.8.15")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}