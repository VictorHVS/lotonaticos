package com.victorhvs.lotonaticos

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LotonaticosApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeFirebase()
    }

    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this)

        val firebaseAppCheck = FirebaseAppCheck.getInstance()

        val appCheckFactory = if (BuildConfig.DEBUG)
            DebugAppCheckProviderFactory.getInstance()
        else
            PlayIntegrityAppCheckProviderFactory.getInstance()

        firebaseAppCheck.installAppCheckProviderFactory(appCheckFactory)
    }
}