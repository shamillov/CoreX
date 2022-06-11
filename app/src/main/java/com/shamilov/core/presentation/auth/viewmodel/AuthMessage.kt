package com.shamilov.core.presentation.auth.viewmodel

sealed class AuthMessage {
    class SendPhone(val phone: String) : AuthMessage()
    class SendVerificationCode(val code: String) : AuthMessage()
    object OnBackButtonClicked : AuthMessage()
}