package com.victorhvs.lotonaticos.data

import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.victorhvs.lotonaticos.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun provideAppCheckFactory(): AppCheckProviderFactory {
        return if (BuildConfig.DEBUG) {
            DebugAppCheckProviderFactory.getInstance()
        } else {
            PlayIntegrityAppCheckProviderFactory.getInstance()
        }
    }
}
