package com.shamilov.core.presentation.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private var state by mutableStateOf<AuthState>(AuthState.PhoneState(isLoading = false, phone = ""))

    fun accept(message: AuthMessage) = when (message) {
        is AuthMessage.SendPhone -> sendPhoneNumber(message.phone)
        is AuthMessage.SendVerificationCode -> sendCode(message.code)
        is AuthMessage.OnBackButtonClicked -> {}
    }

    private fun sendPhoneNumber(phone: String) {
        state = AuthState.PhoneState(isLoading = true, phone)
    }

    private fun sendCode(code: String) {

    }
}