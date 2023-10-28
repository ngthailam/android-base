package vn.thailam.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.thailam.data.datasources.local.AppLocalDatabase
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.data.datasources.remote.ExampleRemoteDataSource
import vn.thailam.data.datasources.remote.ExampleRemoteDataSourceImpl
import vn.thailam.data.repositories.ExampleRepositoryImpl
import vn.thailam.domain.repositories.ExampleRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class ExampleDataModule {
    @Binds
    abstract fun bindsHistoryRemoteDataSource(
        impl: ExampleRemoteDataSourceImpl
    ): ExampleRemoteDataSource

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
    }
}
