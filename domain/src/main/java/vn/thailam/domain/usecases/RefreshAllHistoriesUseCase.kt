package vn.thailam.domain.usecases

import vn.thailam.domain.repositories.HistoryRepository
import javax.inject.Inject

class RefreshAllHistoriesUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke() {
        return historyRepository.refreshHistories()
    }
}
