package com.shamilov.core.presentation.auth.viewmodel

sealed class AuthMessage {
    class SendPhone(val phone: String) : AuthMessage()
    object OnBackButtonClicked : AuthMessage()
}