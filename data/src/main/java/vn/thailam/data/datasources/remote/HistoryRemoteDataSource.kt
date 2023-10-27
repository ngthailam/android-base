package vn.thailam.data.datasources.remote

import vn.thailam.data.models.HistoryEntity
import javax.inject.Inject

interface HistoryRemoteDataSource {
    suspend fun getHistories(): List<HistoryEntity>
}

class HistoryRemoteDataSourceImpl @Inject constructor() : HistoryRemoteDataSource {
    override suspend fun getHistories(): List<HistoryEntity> {
        return listOf(
            HistoryEntity(id = "1"),
            HistoryEntity(id = "2"),
            HistoryEntity(id = "3"),
            HistoryEntity(id = "4"),
            HistoryEntity(id = "5"),
            HistoryEntity(id = "6"),
        )
    }
}
