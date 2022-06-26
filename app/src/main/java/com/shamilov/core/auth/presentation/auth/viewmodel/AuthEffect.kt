package com.shamilov.core.auth.presentation.auth.viewmodel

sealed class AuthEffect {
    object NavigateBack : AuthEffect()
    object OpenVerificationScreen : AuthEffect()
    class ShowErrorMessage(val message: String) : AuthEffect()
}