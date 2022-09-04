plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.android")
    id("com.google.firebase.crashlytics")
    id("org.sonarqube") version "3.4.0.2513"
    id("com.victorhvs.kotlin-quality")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.victorhvs.lotonaticos"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        debug {
            isTestCoverageEnabled = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isTestCoverageEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        warningsAsErrors = true
        abortOnError = true
        htmlReport = true
        checkDependencies = true

        lintConfig = file("${rootDir}/config/filters/lint.xml")
        htmlOutput = file("${buildDir}/reports/lint.html")
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

        freeCompilerArgs = freeCompilerArgs + listOf("-opt-in=kotlin.RequiresOptIn")
    }

    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            unitTests.isReturnDefaultValues = true
        }
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

    androidTestImplementation(libs.test.androidJunitExt)
    androidTestImplementation(libs.test.androidCompose)
    testImplementation(libs.test.mockk)
    testImplementation(libs.test.kotlin)
    testImplementation(libs.test.kotlinCoroutines)
    testImplementation(libs.test.kotest)
    testImplementation(libs.test.junit)

    debugImplementation(libs.test.composeUiTestManifest)
}
