package vn.thailam.data.datasources.remote

import retrofit2.http.GET
import vn.thailam.data.models.ExampleEntity

interface ExampleRemoteDataSource {
    @GET("path/abc")
    suspend fun getExamples(): List<ExampleEntity>
}
