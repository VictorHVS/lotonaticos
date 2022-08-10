plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.android")
    id("com.google.firebase.crashlytics")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdk = Versions.Android.compileSdk

    defaultConfig {
        applicationId = Versions.App.id
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.targetSdk
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlin.Experimental",
            // Enable experimental kotlinx serialization APIs
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
        )
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.JetpackCompose.jetpackCompose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}'"
        }
    }
}

dependencies {
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.lifecycleRuntime)

    implementation(Dependencies.JetpackCompose.ui)
    implementation(Dependencies.JetpackCompose.toolingPreview)
    implementation(Dependencies.JetpackCompose.foundation)
    implementation(Dependencies.JetpackCompose.material3)
    implementation(Dependencies.JetpackCompose.activity)
    implementation(Dependencies.JetpackCompose.appCompatTheme)
    implementation(Dependencies.JetpackCompose.paging)
    implementation(Dependencies.JetpackCompose.swipeRefresh)
    implementation(Dependencies.JetpackCompose.icons)
    implementation(Dependencies.JetpackCompose.navigation)
    implementation(Dependencies.JetpackCompose.hiltNavigation)
    debugImplementation(Dependencies.JetpackCompose.tooling)

    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha15")

    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    implementation(Dependencies.Kotlinx.serialization)
    implementation(Dependencies.Kotlinx.coroutinesCore)
    implementation(Dependencies.Kotlinx.coroutineAndroid)
    implementation(Dependencies.Kotlinx.coroutinePlayService)

    implementation(platform(Dependencies.Firebase.bom))
    implementation(Dependencies.Firebase.analytics)
    implementation(Dependencies.Firebase.auth)
    implementation(Dependencies.Firebase.firestore)
    implementation(Dependencies.Firebase.crashlytics)
    implementation(Dependencies.Firebase.appCheck)
    implementation(Dependencies.Firebase.appCheckDebug)

    implementation(Dependencies.Coil.coil)
    implementation(Dependencies.Coil.coilSvg)

    testImplementation(Dependencies.Test.junit)

    androidTestImplementation(Dependencies.Test.runner)
    androidTestImplementation(Dependencies.Test.rules)
    androidTestImplementation(Dependencies.Test.testExt)
    androidTestImplementation(Dependencies.Test.composeJunit)
    androidTestImplementation(Dependencies.Test.espresso)
    debugImplementation(Dependencies.Test.composeUiTestManifest)
}
