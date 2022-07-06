package com.shamilov.core.profile.presentation.viewmodel

sealed class ProfileEffect {
    object OpenAuthScreen : ProfileEffect()
    class ShowMessage(val message: String): ProfileEffect()
}
