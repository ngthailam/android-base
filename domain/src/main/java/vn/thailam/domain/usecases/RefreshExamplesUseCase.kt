package vn.thailam.domain.usecases

import vn.thailam.domain.repositories.ExampleRepository
import javax.inject.Inject

class RefreshExamplesUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository
) {
    suspend operator fun invoke() {
        return exampleRepository.refreshExamples()
    }
}
