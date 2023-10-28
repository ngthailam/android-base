package vn.thailam.presentation.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vn.thailam.domain.usecases.GetListExampleUseCase
import vn.thailam.domain.usecases.RefreshExamplesUseCase
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val getListExampleUseCase: GetListExampleUseCase,
    private val refreshExamplesUseCase: RefreshExamplesUseCase,
) : ViewModel() {
    val list = getListExampleUseCase()

    init {
        refreshExamples()
    }

    private fun refreshExamples() = viewModelScope.launch {
        refreshExamplesUseCase()
    }
}
