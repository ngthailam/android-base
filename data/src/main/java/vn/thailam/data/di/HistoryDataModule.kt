package vn.thailam.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.data.datasources.local.ExampleLocalDataSourceImpl
import vn.thailam.data.datasources.remote.ExampleRemoteDataSource
import vn.thailam.data.datasources.remote.ExampleRemoteDataSourceImpl
import vn.thailam.data.repositories.ExampleRepositoryImpl
import vn.thailam.domain.repositories.ExampleRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryDataModule {
    @Binds
    abstract fun bindsHistoryRemoteDataSource(
        impl: ExampleRemoteDataSourceImpl
    ): ExampleRemoteDataSource

    @Binds
    abstract fun bindsHistoryLocalDataSource(
        impl: ExampleLocalDataSourceImpl
    ): ExampleLocalDataSource

    @Binds
    abstract fun bindsHistoryRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository
}
