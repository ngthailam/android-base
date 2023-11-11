package vn.thailam.data.datasources.local

import kotlinx.coroutines.flow.Flow
import vn.thailam.data.models.ExampleEntity

interface ExampleLocalDataSource {
    fun getExamples(): Flow<List<ExampleEntity>>

    suspend fun saveExamples(examples: List<ExampleEntity>)
}
