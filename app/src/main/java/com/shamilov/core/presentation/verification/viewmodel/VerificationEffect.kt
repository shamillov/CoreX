package com.shamilov.core.presentation.verification.viewmodel

sealed class VerificationEffect {
    object NavigateBack : VerificationEffect()
    class ShowErrorMessage(val message: String) : VerificationEffect()
    object OpenUserProfileScreen : VerificationEffect()
}