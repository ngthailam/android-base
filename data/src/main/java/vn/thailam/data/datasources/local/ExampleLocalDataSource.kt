package vn.thailam.data.datasources.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import vn.thailam.data.models.ExampleEntity
import javax.inject.Inject

interface ExampleLocalDataSource {
    fun getExamples(): Flow<List<ExampleEntity>>

    fun saveExamples(histories: List<ExampleEntity>)
}

class ExampleLocalDataSourceImpl @Inject constructor() : ExampleLocalDataSource {

    private val _examples = MutableStateFlow<List<ExampleEntity>>(listOf())

    override fun getExamples(): Flow<List<ExampleEntity>> {
        return flow {
            emit(listOf(
                ExampleEntity(id = "1"),
                ExampleEntity(id = "2"),
            ))
        }
    }

    override fun saveExamples(histories: List<ExampleEntity>) {
        _examples.value = histories
    }
}
