package com.shamilov.core.presentation.auth.viewmodel

sealed class AuthState {
    data class PhoneState(
        val isLoading: Boolean,
        val phone: String,
    ) : AuthState()

    data class CodeState(
        val isLoading: Boolean,
        val code: String,
    )
}