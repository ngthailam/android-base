package vn.thailam.domain.usecases

import vn.thailam.core.md5
import vn.thailam.domain.repositories.UserRepository
import java.lang.Exception
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(email: String, rawPassword: String) {
        /**
         * This is for demo purposes so we can register multiple time
         * so we have to delete the old one
         */
        userRepository.delete()

        val user = userRepository.get()
        if (user != null) {
            // TODO: handle when add exception handlers
            throw Exception("User with email $email already exist")
        }

        /**
         * Here i am using md5 for simplicity, but you can switch to AndroidKeyStore
         * by adding a class in data with input: raw password, output: encrypted password
         */
        return userRepository.save(email = email, password = md5(rawPassword))
    }
}
