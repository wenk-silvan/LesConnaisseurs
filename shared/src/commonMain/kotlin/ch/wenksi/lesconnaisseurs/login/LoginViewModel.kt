package ch.wenksi.lesconnaisseurs.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun login(onSuccess: () -> Unit) {
        val trimmedMail = _uiState.value.email.trim()
        val trimmedPw = _uiState.value.password.trim()
        if (trimmedMail.isBlank() || trimmedPw.isBlank()) return

        viewModelScope.launch {
            val result = authRepository.login(
                email = trimmedMail.lowercase(),
                password = trimmedPw,
            )
            if (result.isSuccess) {
                onSuccess()
            }
        }
    }
}
