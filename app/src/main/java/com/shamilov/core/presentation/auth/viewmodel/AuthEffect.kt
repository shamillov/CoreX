package com.shamilov.core.presentation.auth.viewmodel

sealed class AuthEffect {
    object NavigateBack : AuthEffect()
    object OpenVerificationScreen : AuthEffect()
    class ShowErrorMessage(val message: String) : AuthEffect()
}