package vn.thailam.data.datasources.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import vn.thailam.data.datasources.local.UserLocalDataSource
import vn.thailam.data.datasources.local.datastore.entity.UserEntity
import vn.thailam.data.datasources.local.datastore.serializer.UserEntitySerializer
import javax.inject.Inject

class UserDataStore @Inject constructor(
    @ApplicationContext
    private val applicationContext: Context,
) : UserLocalDataSource {
    private val Context.dataStore: DataStore<UserEntity> by dataStore(
        fileName = "user_entity_ds.pb",
        serializer = UserEntitySerializer
    )

    override suspend fun get(): UserEntity? {
        val user = applicationContext.dataStore.data.firstOrNull()
        if (user?.email?.isEmpty() == true) return null
        return user
    }

    override suspend fun save(email: String, password: String) {
        applicationContext.dataStore.updateData { entity ->
            entity.toBuilder()
                // This is just an example
                .setId(1)
                .setEmail(email)
                .setPassword(password)
                .build()
        }
    }

    override suspend fun delete() {
        applicationContext.dataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}
