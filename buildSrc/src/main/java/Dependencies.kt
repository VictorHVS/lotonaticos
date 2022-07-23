object Dependencies {

    object Plugin {
        const val crashlytics =
            "com.google.firebase:firebase-crashlytics-gradle:${Versions.Plugin.crashlytics}"
        const val androidGradlePlugin =
            "com.android.tools.build:gradle:${Versions.Plugin.androidGradle}"
        const val kotlinSerialization =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.Plugin.kotlin}"
        const val googleServices =
            "com.google.gms:google-services:${Versions.Plugin.googleServices}"
        const val kotlinPlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugin.kotlin}"
        const val hiltPlugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.Jetpack.hilt}"
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Plugin.kotlin}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycleRuntime}"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.Jetpack.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.Jetpack.hilt}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val runner = "androidx.test:runner:${Versions.Test.testRunner}"
        const val rules = "androidx.test:rules:${Versions.Test.testRunner}"
        const val testExt = "androidx.test.ext:junit:${Versions.Test.testExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espressoCore}"

        const val composeJunit =
            "androidx.compose.ui:ui-test-junit4:${Versions.JetpackCompose.jetpackCompose}"
        const val composeUiTestManifest =
            "androidx.compose.ui:ui-test-manifest:${Versions.JetpackCompose.jetpackCompose}"
    }

    object Kotlinx {
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlinx.core}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlinx.core}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlinx.serialization}"
    }

    object JetpackCompose {
        const val ui = "androidx.compose.ui:ui:${Versions.JetpackCompose.jetpackCompose}"
        const val tooling =
            "androidx.compose.ui:ui-tooling:${Versions.JetpackCompose.jetpackCompose}"
        const val toolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.JetpackCompose.jetpackCompose}"
        const val foundation =
            "androidx.compose.foundation:foundation:${Versions.JetpackCompose.jetpackCompose}"
        const val material3 =
            "androidx.compose.material3:material3:${Versions.JetpackCompose.material3}"
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

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.Firebase.bom}"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val auth = "com.google.firebase:firebase-auth-ktx"
        const val firestore = "com.google.firebase:firebase-firestore-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val appCheck = "com.google.firebase:firebase-appcheck-playintegrity"
    }
}
