package vn.thailam.data.datasources.local

import vn.thailam.data.datasources.local.datastore.entity.UserEntity

interface UserLocalDataSource {
    suspend fun get(): UserEntity?

    suspend fun save(email: String, password: String)

    suspend fun delete()
}
