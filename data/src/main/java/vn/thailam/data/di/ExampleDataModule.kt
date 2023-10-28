package vn.thailam.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.thailam.data.datasources.local.AppLocalDatabase
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.data.datasources.remote.ExampleRemoteDataSource
import vn.thailam.data.datasources.remote.interceptors.HeaderInterceptor
import vn.thailam.data.repositories.ExampleRepositoryImpl
import vn.thailam.domain.repositories.ExampleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ExampleDataModule {
    @Binds
    abstract fun bindsHistoryRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository

    companion object {
        /**
         * Provides here instead of [LocalDatabaseModule] to decouple
         * ExampleLocalDataSource from AppLocalDatabase
         *
         * When the app gets bigger, you can still move AppLocalDatabase to core
         * and this example module to a feature module that depends on core,
         * and core module doesn't need to know about [ExampleLocalDataSource]
         */
        @Provides
        fun provideExampleLocalDataSource(db: AppLocalDatabase): ExampleLocalDataSource {
            return db.exampleDao()
        }

        @Singleton
        @Provides
        fun providesOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient =
            OkHttpClient
                .Builder()
                .addInterceptor(headerInterceptor)
                .build()

        @Singleton
        @Provides
        fun provideRetrofitInstance(
            okHttpClient: OkHttpClient,
        ): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                /**
                 * When app have flavours, put this into xml resource file based on flavours
                 */
                .baseUrl("https://abc/")
                .client(okHttpClient)
                .build()
        }

        @Singleton
        @Provides
        fun provideExampleRemoteDataSource(retrofit: Retrofit): ExampleRemoteDataSource =
            retrofit.create(ExampleRemoteDataSource::class.java)
    }
}
