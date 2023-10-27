package vn.thailam.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import vn.thailam.data.datasources.local.HistoryLocalDataSource
import vn.thailam.data.datasources.local.HistoryLocalDataSourceImpl
import vn.thailam.data.datasources.remote.HistoryRemoteDataSource
import vn.thailam.data.datasources.remote.HistoryRemoteDataSourceImpl
import vn.thailam.data.repositories.HistoryRepositoryImpl
import vn.thailam.domain.repositories.HistoryRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryDataModule {
    @Binds
    abstract fun bindsHistoryRemoteDataSource(
        impl: HistoryRemoteDataSourceImpl
    ): HistoryRemoteDataSource

    @Binds
    abstract fun bindsHistoryLocalDataSource(
        impl: HistoryLocalDataSourceImpl
    ): HistoryLocalDataSource

    @Binds
    abstract fun bindsHistoryRepository(
        impl: HistoryRepositoryImpl
    ): HistoryRepository
}
