package vn.thailam.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.thailam.data.datasources.remote.HistoryRemoteDataSource
import vn.thailam.data.datasources.local.HistoryLocalDataSource
import vn.thailam.domain.models.History
import vn.thailam.domain.repositories.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val localDataSource: HistoryLocalDataSource,
    private val apiService: HistoryRemoteDataSource,
) : HistoryRepository {
    override fun getHistories(): Flow<List<History>> {
        return localDataSource.getHistories()
            .map { list -> list.map { it.toModel() } }
    }

    override suspend fun refreshHistories() = with(Dispatchers.IO) {
        val histories = apiService.getHistories()
        localDataSource.saveHistories(histories)
    }
}