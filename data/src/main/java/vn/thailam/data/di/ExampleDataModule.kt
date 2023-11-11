package vn.thailam.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.thailam.data.R
import vn.thailam.data.datasources.local.room.AppLocalDatabase
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.data.datasources.local.datastore.ExampleDataStore
import vn.thailam.data.datasources.remote.ExampleRemoteDataSource
import vn.thailam.data.datasources.remote.interceptors.HeaderInterceptor
import vn.thailam.data.repositories.ExampleRepositoryImpl
import vn.thailam.domain.repositories.ExampleRepository
import javax.inject.Named
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
        @Named("ExampleLocalDataSourceRoom")
        fun provideExampleLocalDataSourceRoom(db: AppLocalDatabase): ExampleLocalDataSource {
            return db.exampleDao()
        }

        @Provides
        @Singleton
        @Named("ExampleLocalDataSourceDataStore")
        fun provideExampleLocalDataSourceDataStore(impl: ExampleDataStore): ExampleLocalDataSource {
            return impl
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
            @ApplicationContext
            applicationContext: Context,
            okHttpClient: OkHttpClient,
        ): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                /**
                 * When app have flavours, put this into xml resource file based on flavours
                 */
                .baseUrl(applicationContext.getString(R.string.base_url))
                .client(okHttpClient)
                .build()
        }

        @Singleton
        @Provides
        fun provideExampleRemoteDataSource(retrofit: Retrofit): ExampleRemoteDataSource =
            retrofit.create(ExampleRemoteDataSource::class.java)
    }
}
