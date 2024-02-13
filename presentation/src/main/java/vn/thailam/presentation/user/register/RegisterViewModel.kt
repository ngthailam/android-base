package vn.thailam.presentation.user.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vn.thailam.domain.usecases.RegisterUseCase
import javax.inject.Inject

sealed class RegisterScreenState {
    object Idle : RegisterScreenState()
    object Success : RegisterScreenState()
    data class Error(val throwable: Throwable) : RegisterScreenState()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegisterScreenState>(RegisterScreenState.Idle)
    val uiState: StateFlow<RegisterScreenState> = _uiState

    fun register(email: String, password: String) = viewModelScope.launch {
        try {
            registerUseCase.invoke(email = email, rawPassword = password)
            _uiState.emit(RegisterScreenState.Success)
        } catch (t: Throwable) {
            _uiState.emit(RegisterScreenState.Error(t))
        }
    }

    fun getErrorMessage(): String {
        val msg = (_uiState.value as? RegisterScreenState.Error)?.throwable?.message
        return msg ?: ""
    }
}
