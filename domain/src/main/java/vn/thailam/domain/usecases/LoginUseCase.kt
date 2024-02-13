package vn.thailam.domain.usecases

import vn.thailam.core.md5
import vn.thailam.domain.repositories.UserRepository
import java.lang.Exception
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(email: String, rawPassword: String): Boolean {
        // TODO: handle when add exception handlers
        val user = userRepository.get() ?: throw Exception("User with email $email does NOT exist")
        val isPasswordMatched = user.password == md5(rawPassword)
        if (user.email != email || !isPasswordMatched) {
            throw Exception("Incorrect username or password")
        }

        return true
    }
}
