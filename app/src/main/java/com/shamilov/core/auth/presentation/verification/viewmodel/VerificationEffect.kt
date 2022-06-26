package com.shamilov.core.auth.presentation.verification.viewmodel

sealed class VerificationEffect {
    object NavigateBack : VerificationEffect()
    class ShowErrorMessage(val message: String) : VerificationEffect()
    object OpenUserProfileScreen : VerificationEffect()
}