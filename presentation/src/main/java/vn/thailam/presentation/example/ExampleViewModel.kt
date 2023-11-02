package vn.thailam.presentation.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import vn.thailam.domain.usecases.GetListExampleUseCase
import vn.thailam.domain.usecases.RefreshExamplesUseCase
import vn.thailam.presentation.example.model.ExampleUiModel
import vn.thailam.presentation.example.model.ExampleUiModelMapper
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val exampleUiModelMapper: ExampleUiModelMapper,
    private val getListExampleUseCase: GetListExampleUseCase,
    private val refreshExamplesUseCase: RefreshExamplesUseCase,
) : ViewModel() {
    val list: Flow<List<ExampleUiModel>> = getListExampleUseCase().map { list ->
        exampleUiModelMapper.mapListToUiModel(list)
    }

    init {
        refreshExamples()
    }

    private fun refreshExamples() = viewModelScope.launch {
        refreshExamplesUseCase()
    }
}
