package vn.thailam.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vn.thailam.domain.models.History
import vn.thailam.domain.repositories.HistoryRepository
import javax.inject.Inject

class GetAllHistoriesUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
) {
    operator fun invoke(): Flow<List<History>> {
        return historyRepository.getHistories()
    }
}
