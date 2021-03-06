package com.shamilov.core.auth.presentation.verification.viewmodel

sealed class VerificationMessage {
    class ValidateCode(val code: String) : VerificationMessage()
    object SendCode: VerificationMessage()
    object OnBackButtonClicked : VerificationMessage()
}