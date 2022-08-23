plugins {
//    id("com.android.application")
//    id("com.google.gms.google-services")
//    id("kotlin-android")
//    kotlin("kapt")
    id("dagger.hilt.android.plugin")
//    id("kotlinx-serialization")
//    id("org.jetbrains.kotlin.android")
//    id("com.google.firebase.crashlytics")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.detekt)
//    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktLint)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.victorhvs.lotonaticos"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0.0"

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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}'"
        }
    }
}

dependencies {
//    implementation(libs.AndroidX.coreKtx)
//    implementation(libs.AndroidX.appCompat)
//    implementation(libs.AndroidX.lifecycleRuntime)

    implementation(libs.compose.ui)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.activity)
    implementation(libs.compose.appCompatTheme)
    implementation(libs.compose.paging)
    implementation(libs.compose.icons)
    implementation(libs.compose.navigation)
    implementation(libs.compose.navigationHilt)
    implementation(libs.compose.accompanist.swipeRefresh)
    debugImplementation(libs.compose.tooling)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutine.playservice)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.appCheck)
    implementation(libs.firebase.appCheckDebug)

    implementation(libs.coil)
    implementation(libs.coil.svg)

//    testImplementation(libs.Test.junit)

//    androidTestImplementation(libs.Test.runner)
//    androidTestImplementation(libs.Test.rules)
//    androidTestImplementation(libs.Test.testExt)
//    androidTestImplementation(libs.Test.composeJunit)
//    androidTestImplementation(libs.Test.espresso)
//    debugImplementation(libs.Test.composeUiTestManifest)
}
