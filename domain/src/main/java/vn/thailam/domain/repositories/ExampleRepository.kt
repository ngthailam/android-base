package vn.thailam.domain.repositories

import kotlinx.coroutines.flow.Flow
import vn.thailam.domain.models.Example

interface ExampleRepository {
    fun getExamples(): Flow<List<Example>>

    suspend fun refreshExamples()
}
