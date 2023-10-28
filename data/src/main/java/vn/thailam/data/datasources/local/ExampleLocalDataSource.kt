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
