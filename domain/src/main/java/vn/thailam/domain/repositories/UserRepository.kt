package vn.thailam.domain.repositories

import vn.thailam.domain.models.User

interface UserRepository {
    suspend fun save(email: String, password: String)

    suspend fun get(): User?

    suspend fun delete()
}
