package vn.thailam.presentation.user.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vn.thailam.domain.usecases.LoginUseCase
import javax.inject.Inject

sealed class LoginScreenState {
    object Idle : LoginScreenState()
    object Success : LoginScreenState()
    data class Error(val throwable: Throwable) : LoginScreenState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenState>(LoginScreenState.Idle)
    val uiState: StateFlow<LoginScreenState> = _uiState

    fun login(email: String, password: String) = viewModelScope.launch {
        try {
            val result = loginUseCase.invoke(email = email, rawPassword = password)
            if (result) {
                _uiState.value = LoginScreenState.Success
            } else {
                throw Exception("Cannot login")
            }
        } catch (t: Throwable) {
            _uiState.value = LoginScreenState.Error(t)
        }
    }
}
