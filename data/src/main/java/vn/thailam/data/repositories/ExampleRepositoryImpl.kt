package vn.thailam.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.thailam.data.datasources.remote.ExampleRemoteDataSource
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.domain.models.Example
import vn.thailam.domain.repositories.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val localDataSource: ExampleLocalDataSource,
    private val apiService: ExampleRemoteDataSource,
) : ExampleRepository {
    override fun getExamples(): Flow<List<Example>> {
        return localDataSource.getExamples()
            .map { list -> list.map { it.toModel() } }
    }

    override suspend fun refreshExamples() = with(Dispatchers.IO) {
        val histories = apiService.getExamples()
        localDataSource.saveExamples(histories)
    }
}