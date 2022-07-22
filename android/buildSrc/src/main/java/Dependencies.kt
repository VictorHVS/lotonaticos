object Dependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val material = "com.google.android.material:material:${Versions.material}"

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycleRuntime}"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val runner = "androidx.test:runner:${Versions.testRunner}"
        const val rules = "androidx.test:rules:${Versions.testRunner}"
        const val testExt = "androidx.test.ext:junit:${Versions.testExt}"

        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.JetpackCompose.jetpackCompose}"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.JetpackCompose.jetpackCompose}"
    }

    object Kotlinx {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlinx.core}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlinx.core}"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlinx.serialization}"
    }

    object JetpackCompose {
        const val ui = "androidx.compose.ui:ui:${Versions.JetpackCompose.jetpackCompose}"
        const val tooling =
            "androidx.compose.ui:ui-tooling:${Versions.JetpackCompose.jetpackCompose}"
        const val toolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.JetpackCompose.jetpackCompose}"
        const val foundation =
            "androidx.compose.foundation:foundation:${Versions.JetpackCompose.jetpackCompose}"
        const val material =
            "androidx.compose.material:material:${Versions.JetpackCompose.jetpackCompose}"
        const val activity =
            "androidx.activity:activity-compose:${Versions.JetpackCompose.activity}"
        const val appCompatTheme =
            "com.google.accompanist:accompanist-appcompat-theme:${Versions.JetpackCompose.appCompatTheme}"
        const val paging = "androidx.paging:paging-compose:${Versions.JetpackCompose.paging}"
        const val icons =
            "androidx.compose.material:material-icons-extended:${Versions.JetpackCompose.jetpackCompose}"
        const val navigation =
            "androidx.navigation:navigation-compose:${Versions.JetpackCompose.navigation}"
        const val hiltNavigation =
            "androidx.hilt:hilt-navigation-compose:${Versions.JetpackCompose.hiltNavigation}"

        const val swipeRefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Versions.JetpackCompose.swipeRefresh}"
    }
}
