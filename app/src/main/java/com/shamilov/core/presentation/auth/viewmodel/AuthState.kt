package com.shamilov.core.presentation.auth.viewmodel

data class AuthState(
    val isLoading: Boolean = false,
    val phone: String = "+7",
    val phoneFieldEnabled: Boolean = true,
    val buttonEnabled: Boolean = true,
)