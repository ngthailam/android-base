package vn.thailam.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vn.thailam.domain.usecases.GetAllHistoriesUseCase
import vn.thailam.domain.usecases.RefreshAllHistoriesUseCase
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllHistoriesUseCase: GetAllHistoriesUseCase,
    private val refreshAllHistoriesUseCase: RefreshAllHistoriesUseCase,
) : ViewModel() {
    val allHistories = getAllHistoriesUseCase()

    init {
        abc()
        refreshHistories()
    }

    private fun abc() = viewModelScope.launch {
        getAllHistoriesUseCase().collect {
            println("ZZLL it = $it")
        }
    }

    private fun refreshHistories() = viewModelScope.launch {
        refreshAllHistoriesUseCase()
    }
}
