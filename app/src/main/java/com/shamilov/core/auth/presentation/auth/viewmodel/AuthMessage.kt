package com.shamilov.core.auth.presentation.auth.viewmodel

sealed class AuthMessage {
    class SendPhone(val phone: String) : AuthMessage()
    object OnBackButtonClicked : AuthMessage()
}