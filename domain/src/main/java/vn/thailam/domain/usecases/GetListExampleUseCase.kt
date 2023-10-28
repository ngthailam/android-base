package vn.thailam.domain.usecases

import kotlinx.coroutines.flow.Flow
import vn.thailam.domain.models.Example
import vn.thailam.domain.repositories.ExampleRepository
import javax.inject.Inject

class GetListExampleUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository,
) {
    operator fun invoke(): Flow<List<Example>> {
        return exampleRepository.getExamples()
    }
}
