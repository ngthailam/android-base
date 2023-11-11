package vn.thailam.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.data.datasources.remote.ExampleRemoteDataSource
import vn.thailam.domain.models.Example
import vn.thailam.domain.repositories.ExampleRepository
import javax.inject.Inject
import javax.inject.Named

class ExampleRepositoryImpl @Inject constructor(
    @Named("ExampleLocalDataSourceRoom")
    private val localDataSource: ExampleLocalDataSource,
    private val remoteDataSource: ExampleRemoteDataSource,
) : ExampleRepository {
    override fun getExamples(): Flow<List<Example>> {
        return localDataSource.getExamples()
            .map { list -> list.map { it.toModel() } }
    }

    override suspend fun refreshExamples() = withContext(Dispatchers.IO) {
        try {
            val histories = remoteDataSource.getExamples()
            localDataSource.saveExamples(histories)
        } catch (t: Throwable) {
            /**
             * Do nothing for now
             * TODO: When add ResultState, update this
             */
        }
    }
}