package com.victorhvs.lotonaticos.data.di

import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.firestore.FirebaseFirestore
import com.victorhvs.lotonaticos.BuildConfig
import com.victorhvs.lotonaticos.core.DispatcherProvider
import com.victorhvs.lotonaticos.core.DispatcherProviderImpl
import com.victorhvs.lotonaticos.data.datasource.FirebaseDataSource
import com.victorhvs.lotonaticos.data.datasource.FirebaseDataSourceImp
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

    @Provides
    @Singleton
    fun provideFirestoreClient(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideDisparcherProvider(): DispatcherProvider = DispatcherProviderImpl()

    @Provides
    @Singleton
    fun provideFirestoreDataSource(
        client: FirebaseFirestore,
        dispacher: DispatcherProvider
    ): FirebaseDataSource =
        FirebaseDataSourceImp(
            client = client,
            dispatcher = dispacher
        )
}
