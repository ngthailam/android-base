package vn.thailam.data.datasources.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import vn.thailam.data.models.HistoryEntity
import javax.inject.Inject

interface HistoryLocalDataSource {
    fun getHistories(): Flow<List<HistoryEntity>>

    fun saveHistories(histories: List<HistoryEntity>)
}

class HistoryLocalDataSourceImpl @Inject constructor() : HistoryLocalDataSource {

    private val _histories = MutableStateFlow<List<HistoryEntity>>(listOf())

    override fun getHistories(): Flow<List<HistoryEntity>> {
        return flow {
            emit(listOf(
                HistoryEntity(id = "1"),
                HistoryEntity(id = "2"),
            ))
        }
    }

    override fun saveHistories(histories: List<HistoryEntity>) {
        _histories.value = histories
    }
}
