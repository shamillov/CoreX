package com.shamilov.core.presentation.verification.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class VerificationViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    var state by mutableStateOf(VerificationState())

    private var _effect = MutableSharedFlow<VerificationEffect>()
    val effect: SharedFlow<VerificationEffect> = _effect.asSharedFlow()

    fun accept(message: VerificationMessage) = when (message) {
        is VerificationMessage.OnBackButtonClicked -> navigateBack()
        is VerificationMessage.SendCode -> sendCode()
        is VerificationMessage.ValidateCode -> validateCode(message.code)
    }

    private fun sendCode() {
        state = state.copy(
            isLoading = true,
            codeFieldEnabled = false,
            buttonEnabled = false,
        )
        viewModelScope.launch {
            authUseCase.sendCode(state.code)
                .onSuccess {
                    _effect.emit(VerificationEffect.OpenUserProfileScreen)
                }
                .onFailure {
                    state = state.copy(
                        isLoading = false,
                        codeFieldEnabled = true,
                        buttonEnabled = true,
                    )
                    _effect.emit(VerificationEffect.ShowErrorMessage(it.localizedMessage.orEmpty()))
                }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(VerificationEffect.NavigateBack)
        }
    }

    private fun validateCode(code: String) {
        val isValid = code.isNotBlank() && code.length == 4

        state = state.copy(
            code = code,
            buttonEnabled = isValid
        )
    }
}

class VerificationViewModelFactory(private val authUseCase: AuthUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VerificationViewModel(authUseCase) as T
    }
}