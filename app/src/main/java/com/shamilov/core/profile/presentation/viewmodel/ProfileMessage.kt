package com.shamilov.core.profile.presentation.viewmodel

sealed class ProfileMessage {
    object OnLogoutClicked : ProfileMessage()
}
