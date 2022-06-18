package com.shamilov.core.presentation.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    var state by mutableStateOf(AuthState())

    private var _effect = MutableSharedFlow<AuthEffect>()
    val effect: SharedFlow<AuthEffect> = _effect.asSharedFlow()

    fun accept(message: AuthMessage) = when (message) {
        is AuthMessage.SendPhone -> sendPhoneNumber(message.phone)
        is AuthMessage.OnBackButtonClicked -> navigateBack()
    }

    private fun sendPhoneNumber(phone: String) {
        state = state.copy(
            isLoading = true,
            phone = phone,
            phoneFieldEnabled = false,
            buttonEnabled = false,
        )
        viewModelScope.launch {
            authUseCase.sendPhone(phone)
                .onSuccess {
                    state = state.copy(
                        isLoading = false,
                        phone = phone,
                        buttonEnabled = true,
                        phoneFieldEnabled = true,
                    )
                    _effect.emit(AuthEffect.OpenVerificationScreen)
                }
                .onFailure {
                    _effect.emit(AuthEffect.ShowErrorMessage(it.localizedMessage.orEmpty()))
                }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(AuthEffect.NavigateBack)
        }
    }
}