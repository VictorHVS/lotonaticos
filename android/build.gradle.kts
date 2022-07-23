buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath(Dependencies.Plugin.androidGradlePlugin)
        classpath(Dependencies.Plugin.kotlinPlugin)
        classpath(Dependencies.Plugin.hiltPlugin)
        classpath(Dependencies.Plugin.kotlinSerialization)
        classpath(Dependencies.Plugin.googleServices)
        classpath(Dependencies.Plugin.crashlytics)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
