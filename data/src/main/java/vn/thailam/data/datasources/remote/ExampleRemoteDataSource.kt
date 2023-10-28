package vn.thailam.data.datasources.remote

import vn.thailam.data.models.ExampleEntity
import javax.inject.Inject

interface ExampleRemoteDataSource {
    suspend fun getExamples(): List<ExampleEntity>
}

class ExampleRemoteDataSourceImpl @Inject constructor() : ExampleRemoteDataSource {
    override suspend fun getExamples(): List<ExampleEntity> {
        return listOf(
            ExampleEntity(id = "1"),
            ExampleEntity(id = "2"),
            ExampleEntity(id = "3"),
            ExampleEntity(id = "4"),
            ExampleEntity(id = "5"),
            ExampleEntity(id = "6"),
        )
    }
}
