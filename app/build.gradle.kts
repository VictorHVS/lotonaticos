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

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.lifecycleRuntime)

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
    kapt(libs.hiltCompiler)

    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.serialization)
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

    coreLibraryDesugaring(libs.desugar)

//    testImplementation(Dependencies.Test.junit)
//
//    androidTestImplementation(Dependencies.Test.runner)
//    androidTestImplementation(Dependencies.Test.rules)
//    androidTestImplementation(Dependencies.Test.testExt)
//    androidTestImplementation(Dependencies.Test.composeJunit)
//    androidTestImplementation(Dependencies.Test.espresso)
//    debugImplementation(Dependencies.Test.composeUiTestManifest)
}
