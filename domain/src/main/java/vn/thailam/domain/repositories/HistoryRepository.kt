package vn.thailam.domain.repositories

import kotlinx.coroutines.flow.Flow
import vn.thailam.domain.models.History

interface HistoryRepository {
    fun getHistories(): Flow<List<History>>

    suspend fun refreshHistories()
}
