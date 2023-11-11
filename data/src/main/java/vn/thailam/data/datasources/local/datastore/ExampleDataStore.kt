package vn.thailam.data.datasources.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.data.datasources.local.datastore.entity.ExampleEntityProto
import vn.thailam.data.datasources.local.datastore.entity.ExampleEntityProtoWrapper
import vn.thailam.data.datasources.local.datastore.serializer.ExampleEntitySerializer
import vn.thailam.data.models.ExampleEntity
import javax.inject.Inject

/**
 *
 * Note: For a simple Int/String/Double storage example, check out the official docs
 * https://developer.android.com/topic/libraries/architecture/datastore
 *
 * Using DataStore correctly
 * In order to use DataStore correctly always keep in mind the following rules:
 *
 * Never create more than one instance of DataStore for a given file in the same process. Doing so can break all DataStore functionality. If there are multiple DataStores active for a given file in the same process, DataStore will throw IllegalStateException when reading or updating data.
 *
 * The generic type of the DataStore must be immutable. Mutating a type used in DataStore invalidates any guarantees that DataStore provides and creates potentially serious, hard-to-catch bugs. It is strongly recommended that you use protocol buffers which provide immutability guarantees, a simple API and efficient serialization.
 *
 * Never mix usages of SingleProcessDataStore and MultiProcessDataStore for the same file. If you intend to access the DataStore from more than one process always use MultiProcessDataStore.
 */
class ExampleDataStore @Inject constructor(
    @ApplicationContext
    private val applicationContext: Context,
) : ExampleLocalDataSource {

    private val Context.exampleEntityDataStore: DataStore<ExampleEntityProtoWrapper> by dataStore(
        fileName = "example_entity_ds.pb",
        serializer = ExampleEntitySerializer
    )

    override fun getExamples(): Flow<List<ExampleEntity>> {
        return applicationContext.exampleEntityDataStore.data
            .map { wrapper ->
                wrapper.exampleEntitiesList.map { proto ->
                    proto.toExampleEntity()
                }
            }
    }

    override suspend fun saveExamples(examples: List<ExampleEntity>) {
        applicationContext.exampleEntityDataStore.updateData { entity ->
            entity.toBuilder()
                .addAllExampleEntities(mapExampleEntitiesToProtos(examples))
                .build()
        }
    }

    private fun mapExampleEntitiesToProtos(examples: List<ExampleEntity>): List<ExampleEntityProto> {
        return examples.map { exampleEntity ->
            ExampleEntityProto.getDefaultInstance().toBuilder()
                .setId(exampleEntity.id)
                .build()
        }
    }

    private fun ExampleEntityProto.toExampleEntity(): ExampleEntity {
        return ExampleEntity(
            id = id,
        )
    }
}