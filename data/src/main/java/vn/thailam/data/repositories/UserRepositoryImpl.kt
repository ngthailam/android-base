package vn.thailam.data.repositories

import vn.thailam.data.datasources.local.UserLocalDataSource
import vn.thailam.domain.models.User
import vn.thailam.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
) : UserRepository {
    override suspend fun save(email: String, password: String) {
        return localDataSource.save(email, password)
    }

    override suspend fun get(): User? {
        val userEntity = localDataSource.get() ?: return null
        /**
         * Can change this to a separate Mapper if have more than 1 place to map
         * For simple cases where only 1 mapping case then no need to over complicate
         */
        return User(
            id = userEntity.id.toInt(),
            email = userEntity.email,
            password = userEntity.password,
        )
    }

    override suspend fun delete() {
        return localDataSource.delete()
    }
}
