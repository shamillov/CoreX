package com.shamilov.core.auth.presentation.verification.viewmodel

data class VerificationState(
    val isLoading: Boolean = false,
    val code: String = "",
    val codeFieldEnabled: Boolean = true,
    val buttonEnabled: Boolean = false,
)